package by.balashevich.finalproject.util;

import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class DateConverterTest {

    @Test
    public void convertToLongTest() {
        LocalDate localDate = LocalDate.parse("2020-08-09");
        long expected = 1596920400000L;
        long actual = DateConverter.convertToLong(localDate);

        assertEquals(actual, expected);
    }

    @Test
    public void convertToDateTest() {
        long dateMillis = 1596920400000L;
        LocalDate expected = LocalDate.parse("2020-08-09");
        LocalDate actual = DateConverter.convertToDate(dateMillis);

        assertEquals(actual, expected);
    }
}