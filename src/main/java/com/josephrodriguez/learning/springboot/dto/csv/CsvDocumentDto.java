package com.josephrodriguez.learning.springboot.dto.csv;

import lombok.*;

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
