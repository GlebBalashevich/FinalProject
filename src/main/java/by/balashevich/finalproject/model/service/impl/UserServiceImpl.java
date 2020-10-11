package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.UserDaoImpl;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.model.service.UserService;
import by.balashevich.finalproject.util.PasswordEncryption;
import by.balashevich.finalproject.validator.UserValidator;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class UserServiceImpl implements UserService<User> {
    private static final String EMPTY_VALUE = "";
    private static final String PUNCTUATION_PATTERN = "[\\p{Punct}\\p{Space}]";
    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public boolean add(Map<String, String> clientParameters) throws ServiceProjectException {
        UserValidator userValidator = new UserValidator();
        boolean isClientAdded = false;

        if (userValidator.validateClientParameters(clientParameters)) {
            try {
                String encryptedPassword = PasswordEncryption.encryptPassword(clientParameters.get(PASSWORD));
                clientParameters.put(PASSWORD, encryptedPassword);
                String normalizedPhone = clientParameters.get(PHONE_NUMBER).replaceAll(PUNCTUATION_PATTERN, EMPTY_VALUE);
                clientParameters.put(PHONE_NUMBER, normalizedPhone);
                clientParameters.put(STATUS, Client.Status.PENDING.name());
                clientParameters.put(ROLE, User.Role.CLIENT.name());

                isClientAdded = userDao.add(clientParameters);
            } catch (DaoProjectException | NoSuchAlgorithmException e) {
                throw new ServiceProjectException("Error occurred during adding Client into database", e);
            }
        }

        return isClientAdded;
    }

    @Override
    public boolean remove(User user) throws ServiceProjectException {
        return false;
    }

    @Override
    public User update(User user) throws ServiceProjectException {
        return null;
    }

    public boolean updateClientStatus(String email, Client.Status status) throws ServiceProjectException {
        boolean isParameterUpdated = false;

        try {
            Client.Status currentStatus = userDao.findStatusByEmail(email);
            if (currentStatus != null) {
                if (currentStatus == Client.Status.PENDING) {
                    isParameterUpdated = userDao.updateClientStatus(email, status);
                }
            }
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("An error occurred while updating user parameter", e);
        }

        return isParameterUpdated;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceProjectException {
        UserValidator userValidator = new UserValidator();
        Optional<User> targetUser;

        try {
            if (userValidator.validateEmail(email)) {
                targetUser = userDao.findByEmail(email);
            } else {
                targetUser = Optional.empty();
            }
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("An error occurred during searching user by email", e);
        }

        return targetUser;
    }

    @Override
    public boolean authorizeUser(String email, String password) throws ServiceProjectException {
        UserValidator userValidator = new UserValidator();
        boolean isApproved = false;

        try {
            if (userValidator.validateEmail(email)) {
                String userPassword = userDao.findPasswordByEmail(email);
                if (userPassword != null && !userPassword.isEmpty()) {
                    String verifyingPassword = PasswordEncryption.encryptPassword(password);
                    if (userPassword.equals(verifyingPassword)) {
                        isApproved = true;
                    }
                }
            }
        } catch (DaoProjectException | NoSuchAlgorithmException e) {
            throw new ServiceProjectException("An error occurred while verifying user", e);
        }

        return isApproved;
    }

    @Override
    public boolean existUser(String email) throws ServiceProjectException {
        UserValidator userValidator = new UserValidator();
        boolean isExist = false;

        try {
            if (userValidator.validateEmail(email)) {
                isExist = !userDao.existEmail(email).isEmpty();
            }
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("An error occurred while searching user record", e);
        }

        return isExist;
    }


}
