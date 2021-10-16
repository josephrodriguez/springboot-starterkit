package com.josephrodriguez.learning.springboot.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private final static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public static Date parse(String value) {
        if(StringUtils.isNullOrEmpty(value)) return null;

        try {
            return format.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
