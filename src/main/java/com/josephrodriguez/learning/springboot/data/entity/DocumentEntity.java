package com.josephrodriguez.learning.springboot.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

//Spring JPA annotations
@Entity
@Table(name = "documents")
//Lombok annotations
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DocumentEntity {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "source")
    private String source;

    @Column(name = "code_list_code")
    private String codeListCode;

    @Column(name = "display_value")
    private String displayValue;

    @Column(name = "long_description")
    private String longDescription;

    @Column(name = "from_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Column(name = "to_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date toDate;

    @Column(name = "sorting_priority")
    private Integer sortingPriority;
}
