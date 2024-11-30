package com.main.demo.model.entity;

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
    
    public ClassListId() {
    }
    
    public ClassListId(String cCode, String sCode) {
        this.cCode = cCode;
        this.sCode = sCode;
    }
} 