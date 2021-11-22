package com.olx.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static LocalDate convertStringToDate(String date, String dateFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat, Locale.getDefault());
        return LocalDate.parse(date, dtf);
    }

    public static String convertDateToString(LocalDate date, String dateFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DateUtils.DATE_FORMAT_YYYY_MM_DD, Locale.getDefault());
        return LocalDate.now().format(dtf);
    }
}
