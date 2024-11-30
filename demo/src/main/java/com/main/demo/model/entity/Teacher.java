package com.main.demo.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "teacher")    
@Getter
@Setter
public class Teacher {
    @Id
    @Column(name = "tCode")
    private String tCode;
    
    @Column(name = "tName")
    private String tName;
    @Column(name = "tContact")
    private String tContact;
    @Column(name = "tGrade")
    private int tGrade;

    @OneToMany(mappedBy = "mainTeacher")
    private List<Classroom> mainClasses;

    @OneToMany(mappedBy = "subTeacher")
    private List<Classroom> subClasses;

    @OneToMany(mappedBy = "teacher")
    private List<StudyData> studyDataList;
} 