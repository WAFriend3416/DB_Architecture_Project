package com.main.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ClassListId implements Serializable {
    @Column(name = "sCode")
    private String sCode;
    
    @Column(name = "cCode")
    private String cCode;
} 