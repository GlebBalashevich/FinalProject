package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.UserDaoImpl;
import by.balashevich.finalproject.model.entity.ClientStatus;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.model.service.UserService;
import by.balashevich.finalproject.util.PasswordEncryption;
import by.balashevich.finalproject.validator.UserValidator;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService<User> {
    private static final String EMPTY_VALUE = "";
    private static final String PUNCTUATION_PATTERN = "[\\p{Punct}\\p{Space}]";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String SECOND_NAME = "second_name";
    private static final String DRIVER_LICENSE = "driver_license";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phone_number";

    @Override
    public boolean add(Map<String, String> user) throws ServiceProjectException {
        return false;
    }

    public boolean addClient(Map<String, String> clientParameters) throws ServiceProjectException {
        UserValidator userValidator = new UserValidator();
        UserDaoImpl clientDao = new UserDaoImpl();
        boolean isClientAdded = false;

        if (userValidator.validateClientParameters(clientParameters)) {
            try {
                String login = clientParameters.get(LOGIN);
                String encryptedPassword = PasswordEncryption.encryptPassword(clientParameters.get(PASSWORD));
                String firstName = clientParameters.get(FIRST_NAME);
                String secondName = clientParameters.get(SECOND_NAME);
                String driverLicense = clientParameters.get(DRIVER_LICENSE);
                String email = clientParameters.get(EMAIL);
                String normalizedPhone = clientParameters.get(PHONE_NUMBER).replaceAll(PUNCTUATION_PATTERN, EMPTY_VALUE);
                int phoneNumber = Integer.parseInt(normalizedPhone);
                ClientStatus status = ClientStatus.PENDING;

                isClientAdded = clientDao.addClient(login, encryptedPassword, firstName, secondName,
                        driverLicense, email, phoneNumber, status);
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

    public Optional<User> findUserByLogin(String login) throws ServiceProjectException {
        UserDaoImpl userDao = new UserDaoImpl();
        Optional<User> targetUser;

        try {
            targetUser = userDao.findByLogin(login);
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("An error occurred during searching user by login", e);
        }

        return targetUser;
    }

    public boolean verifyUser(String login, String password) throws ServiceProjectException {
        UserDaoImpl userDao = new UserDaoImpl();
        boolean isApproved = false;

        try {
            String userPassword = userDao.findPasswordByLogin(login);
            if (userPassword != null && !userPassword.isEmpty()) {
                String verifyingPassword = PasswordEncryption.encryptPassword(password);
                if (userPassword.equals(verifyingPassword)) {
                    isApproved = true;
                }
            }
        } catch (DaoProjectException | NoSuchAlgorithmException e) {
            throw new ServiceProjectException("An error occurred while verifying user", e);
        }

        return isApproved;
    }
}
