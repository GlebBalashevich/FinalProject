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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class UserServiceImpl implements UserService<User> {
    private static final String EMPTY_VALUE = "";
    private static final String PUNCTUATION_PATTERN = "[\\p{Punct}\\p{Space}]";
    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public boolean add(Map<String, String> clientParameters) throws ServiceProjectException {
        boolean isClientAdded = false;

        if (UserValidator.validateClientParameters(clientParameters)) {
            try {
                Map<String, Object> preparedClientParameters = new HashMap<>();
                String encryptedPassword = PasswordEncryption.encryptPassword(clientParameters.get(PASSWORD));
                String normalizedPhone = clientParameters.get(PHONE_NUMBER).replaceAll(PUNCTUATION_PATTERN, EMPTY_VALUE);
                long phoneNumber = Integer.parseInt(normalizedPhone);
                preparedClientParameters.put(EMAIL, clientParameters.get(EMAIL));
                preparedClientParameters.put(PASSWORD, encryptedPassword);
                preparedClientParameters.put(ROLE, User.Role.CLIENT);
                preparedClientParameters.put(FIRST_NAME, clientParameters.get(FIRST_NAME));
                preparedClientParameters.put(SECOND_NAME, clientParameters.get(SECOND_NAME));
                preparedClientParameters.put(DRIVER_LICENSE, clientParameters.get(DRIVER_LICENSE));
                preparedClientParameters.put(PHONE_NUMBER, phoneNumber);
                preparedClientParameters.put(USER_STATUS, Client.Status.PENDING.name());
                isClientAdded = userDao.add(preparedClientParameters);
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

    @Override
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
        Optional<User> targetUser;

        try {
            if (UserValidator.validateEmail(email)) {
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
        boolean isApproved = false;

        try {
            if (UserValidator.validateEmail(email)) {
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
        boolean isExist = false;

        try {
            if (UserValidator.validateEmail(email)) {
                isExist = !userDao.existEmail(email).isEmpty();
            }
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("An error occurred while searching user record", e);
        }

        return isExist;
    }


}
