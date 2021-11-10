package com.josephrodriguez.learning.springboot.utils;

import java.util.function.Function;

public class StringFuncs {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    public static String thenIfEmpty(String str, String value) {
        return isNotNullOrEmpty(str) ? str : value;
    }

    public static String thenIfNull(String str, String value) {
        return str == null ? value : str;
    }
}
