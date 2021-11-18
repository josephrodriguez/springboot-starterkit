package com.josephrodriguez.learning.springboot.dto.csv;

import com.josephrodriguez.learning.springboot.annotation.CsvColumn;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentCsvDto {

    @CsvColumn
    public String code;

    @CsvColumn(sort = 1)
    private String codeListCode;

    @CsvColumn(sort = 2)
    private String source;

    @CsvColumn(sort = 3)
    private String displayValue;

    @CsvColumn(sort = 4)
    private String longDescription;

    @CsvColumn(column = "fromDate", sort = 5)
    private Date fromDate;

    @CsvColumn(column = "toDate", sort = 6)
    private Date toDate;

    @CsvColumn(sort = 7)
    private Integer sortingPriority;
}
