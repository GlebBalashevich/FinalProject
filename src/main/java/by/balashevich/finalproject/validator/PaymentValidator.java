package by.balashevich.finalproject.validator;

import java.time.LocalDate;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

/**
 * The type Payment validator.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class PaymentValidator {
    private static final String CARD_HOLDER_PATTERN = "^\\p{Alpha}{1,20} \\p{Alpha}{1,20}$";
    private static final String CARD_NUMBER_PATTERN = "^\\p{Digit}{16}$";
    private static final String CARD_CVV_PATTERN = "^\\p{Digit}{3}$";
    private static final String DATE_PART_PATTERN = "^\\p{Digit}{2}$";

    private PaymentValidator() {
    }

    /**
     * Validate payment parameters boolean.
     *
     * @param paymentParameters the payment parameters
     * @return the boolean
     */
    public static boolean validatePaymentParameters(Map<String, String> paymentParameters) {
        boolean isPaymentCorrect = true;
        if (!validateCardHolder(paymentParameters.get(CARD_HOLDER))) {
            isPaymentCorrect = false;
        }
        if (!validateCardNumber(paymentParameters.get(CARD_NUMBER))) {
            isPaymentCorrect = false;
        }
        if (!validateExpirationDate(paymentParameters.get(CARD_EXPIRATION_MONTH),
                paymentParameters.get(CARD_EXPIRATION_YEAR))) {
            isPaymentCorrect = false;
        }
        if (!validateCvvCode(paymentParameters.get(CARD_CVV_CODE))) {
            isPaymentCorrect = false;
        }

        return isPaymentCorrect;
    }

    /**
     * Validate card holder boolean.
     *
     * @param cardHolderData the card holder data
     * @return the boolean
     */
    public static boolean validateCardHolder(String cardHolderData) {
        boolean isCardHolderValid = false;
        if (cardHolderData != null && !cardHolderData.isEmpty()) {
            isCardHolderValid = cardHolderData.matches(CARD_HOLDER_PATTERN);
        }

        return isCardHolderValid;
    }

    /**
     * Validate card number boolean.
     *
     * @param cardNumberData the card number data
     * @return the boolean
     */
    public static boolean validateCardNumber(String cardNumberData) {
        boolean isCardNumberValid = false;
        if (cardNumberData != null && !cardNumberData.isEmpty()) {
            if (cardNumberData.matches(CARD_NUMBER_PATTERN)) {
                isCardNumberValid = Long.parseLong(cardNumberData) > 0;
            }
        }

        return isCardNumberValid;
    }

    /**
     * Validate expiration date boolean.
     *
     * @param monthData the month data
     * @param yearData  the year data
     * @return the boolean
     */
    public static boolean validateExpirationDate(String monthData, String yearData) {
        boolean isExpirationDateValid = false;
        if (monthData != null && !monthData.isEmpty() && yearData != null && !yearData.isEmpty()) {
            if (monthData.matches(DATE_PART_PATTERN) && yearData.matches(DATE_PART_PATTERN)) {
                LocalDate currentDate = LocalDate.now();
                int currentMonth = currentDate.getMonth().getValue();
                int currentYear = currentDate.getYear() % 2000;
                int month = Integer.parseInt(monthData);
                int year = Integer.parseInt(yearData);
                if (year > currentYear) {
                    isExpirationDateValid = (month >= 1 && month <= 12);
                }
                if (year == currentYear) {
                    isExpirationDateValid = (month >= currentMonth && month <= 12);
                }
            }
        }

        return isExpirationDateValid;
    }

    /**
     * Validate cvv code boolean.
     *
     * @param cvvCodeData the cvv code data
     * @return the boolean
     */
    public static boolean validateCvvCode(String cvvCodeData) {
        boolean isCvvCodeValid = false;
        if (cvvCodeData != null && !cvvCodeData.isEmpty()) {
            if (cvvCodeData.matches(CARD_CVV_PATTERN)) {
                isCvvCodeValid = Integer.parseInt(cvvCodeData) > 0;
            }
        }

        return isCvvCodeValid;
    }
}
