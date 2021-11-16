package com.josephrodriguez.learning.springboot.services.csv;

import com.josephrodriguez.learning.springboot.annotation.CsvColumn;
import com.josephrodriguez.learning.springboot.utils.StringFuncs;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvColumnMapper {

    public <T> List<CsvColumnMapping> getClassMapping(Class<T> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .map(this::getFieldMapping)
                .sorted(Comparator.comparingInt(CsvColumnMapping::getSort))
                .collect(Collectors.toList());
    }

    public CsvColumnMapping getFieldMapping(Field field) {
        CsvColumn annotation = field.getAnnotation(CsvColumn.class);
        int sort = annotation.sort();
        String header = StringFuncs.thenIfEmpty(annotation.column(), field.getName());

        return new CsvColumnMapping(field, sort, header);
    }
}
