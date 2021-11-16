package com.josephrodriguez.learning.springboot.exceptions;

public class CsvException extends Exception {

    public CsvException(String message) {
        super(message);
    }

    public CsvException(String message, Throwable cause) {
        super(message, cause);
    }
}
