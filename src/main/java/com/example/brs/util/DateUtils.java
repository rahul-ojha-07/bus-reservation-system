package com.example.brs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Date today() {
        return new Date();
    }

    public static String todayToString() {
        return sdf.format(today());
    }

    public static String formattedDate(Date date) {
        return date != null? sdf.format(date) : todayToString();
    }
}
