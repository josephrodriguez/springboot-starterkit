package com.gerimedica.springboot.data.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "OBSERVATION")
public class Observation {

    @Id
    @Column(name = "OBSERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long observationId;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "CODE_LIST_CODE")
    private String codeListCode;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DISPLAY_VALUE")
    private String displayValue;

    @Column(name = "LONG_DESCRIPTION")
    private String longDescription;

    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Column(name = "TO_DATE")
    private Date toDate;

    @Column(name = "SORTING_PRIORITY")
    private Integer sortingPriority;

    public long getObservationId() {
        return observationId;
    }

    public void setObservationId(long observationId) {
        this.observationId = observationId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCodeListCode() {
        return codeListCode;
    }

    public void setCodeListCode(String codeListCode) {
        this.codeListCode = codeListCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Integer getSortingPriority() {
        return sortingPriority;
    }

    public void setSortingPriority(Integer sortingPriority) {
        this.sortingPriority = sortingPriority;
    }
}
