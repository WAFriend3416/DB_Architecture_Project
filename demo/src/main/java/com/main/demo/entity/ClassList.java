package com.main.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "classList")
@Getter
@Setter
public class ClassList {
    @EmbeddedId
    private ClassListId id;

    @ManyToOne
    @MapsId("sCode")
    @JoinColumn(name = "sCode")
    private Student student;

    @ManyToOne
    @MapsId("cCode")
    @JoinColumn(name = "cCode")
    private Classroom classroom;
} 