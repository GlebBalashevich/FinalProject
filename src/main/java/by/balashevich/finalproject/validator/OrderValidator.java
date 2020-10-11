package by.balashevich.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderValidator {
    private static final String DATE_PATTERN = "(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2})";

    public boolean validateDateData(String dateData){
        boolean isDateValid = false;

        if(dateData != null && dateData.matches(DATE_PATTERN)){
            Pattern pattern = Pattern.compile(DATE_PATTERN);
            Matcher matcher = pattern.matcher(dateData);
            if(matcher.find()) {
                int groupIndex = 0;
                int year = Integer.parseInt(matcher.group(++groupIndex));
                int month = Integer.parseInt(matcher.group(++groupIndex));
                int day = Integer.parseInt(matcher.group(++groupIndex));
                int hour = Integer.parseInt(matcher.group(++groupIndex));
                int minute = Integer.parseInt(matcher.group(++groupIndex));
                if (year > 2000 && year < 2050 && month > 0 && month <= 12
                        && hour >= 0 && hour < 24 && minute >= 0 && minute < 60){
                    isDateValid = switch (month){
                        case 1, 3, 5, 7, 8, 10, 12 -> day > 0 && day < 31;
                        case 2 -> day > 0 && day < 29;
                        case 4, 6, 9, 11 -> day > 0 && day < 30;
                        default -> false;
                    };
                }
            }
        }

        return isDateValid;
    }
}
