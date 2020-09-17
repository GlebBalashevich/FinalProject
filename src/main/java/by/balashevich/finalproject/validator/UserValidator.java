package by.balashevich.finalproject.validator;

import java.util.Map;

public class UserValidator {
    private static final String EMPTY_VALUE = "";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirm_password";
    private static final String FIRST_NAME = "first_name";
    private static final String SECOND_NAME = "second_name";
    private static final String DRIVER_LICENSE = "driver_license";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phone_number";

    private static final String LOGIN_PATTERN = "^[\\p{Alnum}._-]{3,25}$";
    private static final String PASSWORD_PATTERN = "((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*[_-])?.{8,})";
    private static final String NAME_PATTERN = "[\\p{Alpha}-]{1,20}";
    private static final String DRIVER_LICENSE_PATTERN = "(\\p{Digit}?\\p{Alpha}{2}\\s?\\p{Digit}{6})";
    private static final String EMAIL_PATTERN = "^\\p{Alnum}+[._-]?\\p{Alnum}+@\\p{Alnum}+\\.\\p{Alpha}{2,4}";
    private static final String PHONE_NUMBER_PATTERN = "^(\\+)?([-_():\\s]?\\d[-_():\\s]?){12}?$";

    public boolean validateClientParameters(Map<String, String> clientParameters) {
        boolean isParametersCorrect = true;
        if (!validateLogin(clientParameters.get(LOGIN))) {
            isParametersCorrect = false;
            clientParameters.put(LOGIN, EMPTY_VALUE);
        }
        if (!validatePasswords(clientParameters.get(PASSWORD), clientParameters.get(CONFIRM_PASSWORD))) {
            isParametersCorrect = false;
            clientParameters.put(PASSWORD, EMPTY_VALUE);
        }
        if (!validateName(clientParameters.get(FIRST_NAME))){
            isParametersCorrect = false;
            clientParameters.put(FIRST_NAME, EMPTY_VALUE);
        }
        if (!validateName(clientParameters.get(SECOND_NAME))){
            isParametersCorrect = false;
            clientParameters.put(SECOND_NAME, EMPTY_VALUE);
        }
        if (!validateDriverLicense(clientParameters.get(DRIVER_LICENSE))){
            isParametersCorrect = false;
            clientParameters.put(DRIVER_LICENSE, EMPTY_VALUE);
        }
        if(!validateEmail(clientParameters.get(EMAIL))){
            isParametersCorrect = false;
            clientParameters.put(EMAIL, EMPTY_VALUE);
        }
        if (!validatePhoneNumber(clientParameters.get(PHONE_NUMBER))){
            isParametersCorrect = false;
            clientParameters.put(PHONE_NUMBER, EMPTY_VALUE);
        }

        return isParametersCorrect;
    }

    public boolean validateLogin(String login) {
        boolean isLoginCorrect = false;
        if (login != null && !login.isEmpty()) {
            isLoginCorrect = login.matches(LOGIN_PATTERN);
        }

        return isLoginCorrect;
    }

    public boolean validatePasswords(String password, String confirmPassword) {
        boolean isPasswordsCorrect = false;
        if (password != null && confirmPassword != null) {
            if (!password.isEmpty() && password.equals(confirmPassword)) {
                isPasswordsCorrect = password.matches(PASSWORD_PATTERN);
            }
        }

        return isPasswordsCorrect;
    }

    public boolean validateName(String name) {
        boolean isNameCorrect = false;
        if (name != null && !name.isEmpty()) {
            isNameCorrect = name.matches(NAME_PATTERN);
        }

        return isNameCorrect;
    }

    public boolean validateDriverLicense(String driverLicense) {
        boolean isLicenseCorrect = false;
        if (driverLicense != null && !driverLicense.isEmpty()) {
            isLicenseCorrect = driverLicense.matches(DRIVER_LICENSE_PATTERN);
        }

        return isLicenseCorrect;
    }

    public boolean validateEmail(String email) {
        boolean isEmailCorrect = false;
        if (email != null && !email.isEmpty()) {
            isEmailCorrect = email.matches(EMAIL_PATTERN);
        }

        return isEmailCorrect;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        boolean isPhoneNumberCorrect = false;
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            isPhoneNumberCorrect = phoneNumber.matches(PHONE_NUMBER_PATTERN);
        }

        return isPhoneNumberCorrect;
    }
}
