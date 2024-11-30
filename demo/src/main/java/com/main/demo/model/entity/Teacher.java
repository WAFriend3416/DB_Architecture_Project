package com.main.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Entity
@Table(name = "teacher")    
@Getter
@Setter
public class Teacher {
    @Id
    @Column(name = "tCode")
    @JsonProperty("tCode")
    private String tCode;
    
    @Column(name = "tName")
    @JsonProperty("tName")
    private String tName;
    
    @Column(name = "tContact")
    @JsonProperty("tContact")
    private String tContact;
    
    @Column(name = "tGrade")
    @JsonProperty("tGrade")
    private int tGrade;

    @JsonIgnore
    @OneToMany(mappedBy = "mainTeacher")
    private List<Classroom> mainClasses;

    @JsonIgnore
    @OneToMany(mappedBy = "subTeacher")
    private List<Classroom> subClasses;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<StudyData> studyDataList;
} 