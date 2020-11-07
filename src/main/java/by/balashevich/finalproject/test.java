package by.balashevich.finalproject;

import by.balashevich.finalproject.util.DateConverter;

import java.time.LocalDate;

public class test {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.parse("2020-08-09");
        long date = DateConverter.convertToLong(ld);
        System.out.println(date);
        System.out.println(DateConverter.convertToDate(date));
    }
}
