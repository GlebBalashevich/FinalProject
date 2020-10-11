package by.balashevich.finalproject.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm";

    public Date parseDate(String textDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        Date targetDate = dateFormat.parse(textDate);

        return targetDate;
    }
}
