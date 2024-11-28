package com.main.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "classroom")
@Getter
@Setter
public class Classroom {
    @Id
    @Column(name = "cCode")
    private String cCode;
    
    @Column(name = "cName")
    private String cName;

    @ManyToOne
    @JoinColumn(name = "tMain")
    private Teacher mainTeacher;

    @ManyToOne
    @JoinColumn(name = "tSub")
    private Teacher subTeacher;

    @OneToMany(mappedBy = "classroom")
    private List<ClassList> classLists;

    @OneToMany(mappedBy = "classroom")
    private List<StudyData> studyDataList;
} 