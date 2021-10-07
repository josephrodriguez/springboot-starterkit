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
}
