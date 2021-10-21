package com.josephrodriguez.learning.springboot.dto.csv;

import com.josephrodriguez.learning.springboot.annotation.CsvColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CsvDocumentDto {

    @CsvColumn(column = "code")
    private String code;

    @CsvColumn(column = "codeListCode", sort = 1)
    private String codeListCode;

    @CsvColumn(column = "source", sort = 1)
    private String source;

    @CsvColumn(column = "displayValue", sort = 2)
    private String displayValue;

    @CsvColumn(column = "longDescription", sort = 3)
    private String longDescription;

    @CsvColumn(column = "from_date", sort = 4)
    private Date fromDate;

    @CsvColumn(column = "to_date", sort = 5)
    private Date toDate;

    @CsvColumn(column = "sortPriority", sort = 6)
    private Integer sortingPriority;
}
