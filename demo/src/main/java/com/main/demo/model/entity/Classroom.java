package com.main.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Entity
@Table(name = "classroom")
@Getter
@Setter
public class Classroom {
    @Id
    @Column(name = "cCode")
    @JsonProperty("cCode")
    private String cCode;
    
    @Column(name = "cName")
    @JsonProperty("cName")
    private String cName;

    @ManyToOne
    @JoinColumn(name = "tMain")
    private Teacher mainTeacher;

    @ManyToOne
    @JoinColumn(name = "tSub")
    private Teacher subTeacher;

    @JsonIgnore
    @OneToMany(mappedBy = "classroom")
    private List<ClassList> classLists;

    @JsonIgnore
    @OneToMany(mappedBy = "classroom")
    private List<StudyData> studyDataList;
} 