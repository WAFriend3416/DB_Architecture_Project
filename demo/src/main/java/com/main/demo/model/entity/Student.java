package com.main.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "sCode")
    @JsonProperty("sCode")
    private String sCode;
    
    @Column(name = "sName")
    @JsonProperty("sName")
    private String sName;
    
    @Column(name = "sAge")
    @JsonProperty("sAge")
    private int sAge;
    
    @Column(name = "sContact")
    @JsonProperty("sContact")
    private String sContact;
    
    @Column(name = "seContact")
    @JsonProperty("seContact")
    private String seContact;
    
    @Column(name = "sCSchool")
    @JsonProperty("sCSchool")
    private String sCSchool;
    
    @Column(name = "sESchool")
    @JsonProperty("sESchool")
    private String sESchool;
    
    @Column(name = "sMSchool")
    @JsonProperty("sMSchool")
    private String sMSchool;
    
    @Column(name = "sHSchool")
    @JsonProperty("sHSchool")
    private String sHSchool;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<ClassList> classLists;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<StudyData> studyDataList;
} 