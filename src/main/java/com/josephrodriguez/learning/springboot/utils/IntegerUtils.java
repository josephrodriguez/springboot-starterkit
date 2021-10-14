package com.josephrodriguez.learning.springboot.utils;

public class IntegerUtils {

    public static Integer parse(String value) {
        try {
            return Integer.valueOf(value);
        } catch(NumberFormatException ex) {
            return null;
        }
    }
}
