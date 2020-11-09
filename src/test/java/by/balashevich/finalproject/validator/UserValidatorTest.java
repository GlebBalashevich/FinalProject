package by.balashevich.finalproject.validator;

import by.balashevich.finalproject.util.ParameterKey;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class UserValidatorTest {

    @Test
    public void validateClientParameters() {
        Map<String, String> clientParameters = new HashMap<>();
        clientParameters.put(ParameterKey.EMAIL, "user@companyname.by");
        clientParameters.put(ParameterKey.PASSWORD, "User1234");
        clientParameters.put(ParameterKey.CONFIRM_PASSWORD, "User1234");
        clientParameters.put(ParameterKey.FIRST_NAME, "Petr");
        clientParameters.put(ParameterKey.SECOND_NAME, "Petrov");
        clientParameters.put(ParameterKey.DRIVER_LICENSE, "7AB123456");
        clientParameters.put(ParameterKey.PHONE_NUMBER, "375292020327");
        assertTrue(UserValidator.validateClientParameters(clientParameters));
    }

    @DataProvider(name = "emailData")
    public Object[][] createEmailData() {
        return new Object[][]{
                {"user@bestcompany.by", true},
                {"1234@105.by", true},
                {"user@bestcompany.byebye", false},
                {"<script>@kill.by", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "emailData")
    public void validateEmailTest(String email, boolean expected) {
        boolean actual = UserValidator.validateEmail(email);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "passwordsData")
    public Object[][] createPasswordsData() {
        return new Object[][]{
                {"Admin1234", "Admin1234", true},
                {"Admin1234", "anyElse1234", false},
                {"admin1234", "admin1234", false},
                {"Ad1234", "Ad1234", false},
                {"Bestadmin", "Bestadmin", false},
                {"Admin1234", null, false},
                {null, "Admin1234", false},
                {"Admin1234", "", false},
                {"", "Admin1234", false},
        };
    }

    @Test(dataProvider = "passwordsData")
    public void validatePasswordsTest(String password, String confrimPassword, boolean expected) {
        boolean actual = UserValidator.validatePasswords(password, confrimPassword);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "nameData")
    public Object[][] createNameData() {
        return new Object[][]{
                {"Petr", true},
                {"Petr1", false},
                {"TwentySymbolsTwentySymbols", false},
                {"1234", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "nameData")
    public void validateNameTest(String name, boolean expected) {
        boolean actual = UserValidator.validateName(name);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "driverLicenseData")
    public Object[][] createDriverLicenseData() {
        return new Object[][]{
                {"7AB123456", true},
                {"AB123456", true},
                {"AB1", false},
                {"111111111", false},
                {"AAAAAAAAA", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "driverLicenseData")
    public void validateDriverLicenseTest(String driverLicense, boolean expected) {
        boolean actual = UserValidator.validateDriverLicense(driverLicense);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "phoneNumberData")
    public Object[][] createPhoneNumberData() {
        return new Object[][]{
                {"+375292020327", true},
                {"+375(29)202-03-27", true},
                {null, true},
                {"+375(29)146", false},
                {"375x29x202x03x27", false},
                {"12345678910", false},
                {"", false},
        };
    }

    @Test(dataProvider = "phoneNumberData")
    public void validatePhoneNumberTest(String phoneNumber, boolean expected) {
        boolean actual = UserValidator.validatePhoneNumber(phoneNumber);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "clientStatusData")
    public Object[][] createClientStatusData() {
        return new Object[][]{
                {"pending", true},
                {"BLOCKED", true},
                {"0", false},
                {"1", false},
                {"any", false},
                {"1234", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "clientStatusData")
    public void validateClientStatusTest(String clientStatus, boolean expected) {
        boolean actual = UserValidator.validateClientStatus(clientStatus);
        assertEquals(actual, expected);
    }
}