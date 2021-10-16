package com.josephrodriguez.learning.springboot.dto.csv;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//Lombok annotations
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CsvDocumentDto {

    private String code;

    private String codeListCode;

    private String source;

    private String displayValue;

    private String longDescription;

    private Date fromDate;

    private Date toDate;

    private Integer sortingPriority;
}
