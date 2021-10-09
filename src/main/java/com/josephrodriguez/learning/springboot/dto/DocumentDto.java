package com.josephrodriguez.learning.springboot.dto;

import java.util.Date;

public class DocumentDto {

    private String code;

    private String codeListCode;

    private String displayValue;

    private String longDescription;

    private Date from;

    private Date to;

    public DocumentDto(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getCodeListCode() {
        return codeListCode;
    }

    public void setCodeListCode(String codeListCode) {
        this.codeListCode = codeListCode;
    }
}
