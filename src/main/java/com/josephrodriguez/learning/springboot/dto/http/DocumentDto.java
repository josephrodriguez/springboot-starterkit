package com.josephrodriguez.learning.springboot.dto.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentDto {

    private String code;

    private String codeListCode;

    private String source;

    private String displayValue;

    private String longDescription;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fromDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date toDate;

    private Integer sortingPriority;
}
