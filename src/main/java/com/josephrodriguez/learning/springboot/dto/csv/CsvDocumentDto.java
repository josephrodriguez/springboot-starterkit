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

    @CsvColumn(column = "from_date", sort = 5)
    private Date fromDate;

    @CsvColumn(column = "to_date", sort = 6)
    private Date toDate;

    @CsvColumn(sort = 7)
    private Integer sortingPriority;
}
