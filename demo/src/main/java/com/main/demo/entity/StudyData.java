package com.main.demo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "studyData")
@Getter
@Setter
public class StudyData {
    @EmbeddedId
    private StudyDataId id;

    @ManyToOne
    @MapsId("sCode")
    @JoinColumn(name = "sCode")
    private Student student;

    @ManyToOne
    @MapsId("cCode")
    @JoinColumn(name = "cCode")
    private Classroom classroom;

    @Column(name = "score1")
    private Float score1;

    @Column(name = "score2")
    private Float score2;

    @Column(name = "score3")
    private Float score3;

    @Column(name = "score4")
    private Float score4;

    @ManyToOne
    @JoinColumn(name = "tCode")
    private Teacher teacher;
}