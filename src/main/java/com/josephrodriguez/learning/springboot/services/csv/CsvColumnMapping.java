package com.josephrodriguez.learning.springboot.services.csv;

import java.lang.reflect.Field;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CsvColumnMapping {

    private final Field field;
    private final int sort;
    private final String header;
}
