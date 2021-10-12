package com.josephrodriguez.learning.springboot.utils.mapping;

import com.josephrodriguez.learning.springboot.data.entity.Document;
import com.josephrodriguez.learning.springboot.utils.DateUtils;
import com.josephrodriguez.learning.springboot.utils.IntegerUtils;
import org.apache.commons.csv.CSVRecord;

public class CsvRecordMapper {

    public static Document toDocument(CSVRecord record) {
        Document document = new Document();

        document.setSource(record.get(0));
        document.setCodeListCode(record.get(1));
        document.setCode(record.get(2));
        document.setDisplayValue(record.get(3));
        document.setLongDescription(record.get(4));
        document.setFromDate(DateUtils.parse(record.get(5)));
        document.setToDate(DateUtils.parse(record.get(6)));
        document.setSortingPriority(IntegerUtils.parse(record.get(7), null));

        return document;
    }
}
