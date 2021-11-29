package com.minibank.util;

import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utils {

    public static String getCurrentMonthLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM").withLocale(locale);
        LocalDate d = LocalDate.now();
        return d.format(df);
    }
}
