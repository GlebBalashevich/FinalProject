package by.balashevich.finalproject.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateConverter {

    private DateConverter() {
    }

    public static long convertToLong(LocalDate date) {
        ZonedDateTime zonedDateTime = date.atStartOfDay().atZone(ZoneId.systemDefault());

        return zonedDateTime.toInstant().toEpochMilli();
    }

    public static LocalDate convertToDate(long milliseconds) {
        return Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
