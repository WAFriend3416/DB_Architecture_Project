package com.main.demo.model.entity;


import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class StudyDataId implements Serializable {
    @Column(name = "date_column")
    private LocalDate date_column;
    
    @Column(name = "sCode")
    private String sCode;
    
    @Column(name = "cCode")
    private String cCode;
}