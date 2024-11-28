package com.main.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {
    @Id
    @Column(name = "sCode")
    private String sCode;
    
    @Column(name = "sName")
    private String sName;
    
    @Column(name = "sAge")
    private int sAge;
    
    @Column(name = "sContact")
    private String sContact;
    
    @Column(name = "seContact")
    private String seContact;
    
    @Column(name = "sCSchool")
    private String sCSchool;
    
    @Column(name = "sESchool")
    private String sESchool;
    
    @Column(name = "sMSchool")
    private String sMSchool;
    
    @Column(name = "sHSchool")
    private String sHSchool;

    @OneToMany(mappedBy = "student")
    private List<ClassList> classLists;

    @OneToMany(mappedBy = "student")
    private List<StudyData> studyDataList;
} 