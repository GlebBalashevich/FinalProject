package by.balashevich.finalproject.validator;

import by.balashevich.finalproject.util.ParameterKey;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class PaymentValidatorTest {

    @Test
    public void validatePaymentParametersTest() {
        Map<String, String> paymentParameters = new HashMap<>();
        paymentParameters.put(ParameterKey.CARD_HOLDER, "Ivanov Ivan");
        paymentParameters.put(ParameterKey.CARD_NUMBER, "1111222233334444");
        paymentParameters.put(ParameterKey.CARD_EXPIRATION_MONTH, "12");
        paymentParameters.put(ParameterKey.CARD_EXPIRATION_YEAR, "40");
        paymentParameters.put(ParameterKey.CARD_CVV_CODE, "123");
        boolean actual = PaymentValidator.validatePaymentParameters(paymentParameters);
        assertTrue(actual);
    }

    @DataProvider(name = "cardHolderData")
    public Object[][] createCardHolderData() {
        return new Object[][]{
                {"Ivan Ivanov", true},
                {"1234 1234", false},
                {null, false},
                {"", false}
        };
    }

    @Test(dataProvider = "cardHolderData")
    public void validateCardHolderTest(String cardHolder, boolean expected) {
        boolean actual = PaymentValidator.validateCardHolder(cardHolder);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "cardNumberData")
    public Object[][] createCardNumberData() {
        return new Object[][]{
                {"1234567887654321", true},
                {"1234ABCD43211234", false},
                {"0000000000000000", false},
                {null, false},
                {"", false}
        };
    }

    @Test(dataProvider = "cardNumberData")
    public void validateCardNumberTest(String cardNumber, boolean expected) {
        boolean actual = PaymentValidator.validateCardNumber(cardNumber);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "expirationDateData")
    public Object[][] createExpirationDateData() {
        return new Object[][]{
                {"12", "45", true},
                {"14", "45", false},
                {"12", "00", false},
                {"aa", "45", false},
                {"12", "gg", false},
                {null, "45", false},
                {"12", null, false},
                {"", "45", false},
                {"12", "", false}
        };
    }

    @Test(dataProvider = "expirationDateData")
    public void validateExpirationDateTest(String month, String year, boolean expected) {
        boolean actual = PaymentValidator.validateExpirationDate(month, year);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "cvvCodeData")
    public Object[][] createCvvCodeData() {
        return new Object[][]{
                {"123", true},
                {"320", true},
                {"abc", false},
                {null, false},
                {"", false}
        };
    }

    @Test(dataProvider = "cvvCodeData")
    public void validateCvvCodeTest(String cvvCode, boolean expected) {
        boolean actual = PaymentValidator.validateCvvCode(cvvCode);
        assertEquals(actual, expected);
    }
}