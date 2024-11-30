package com.main.demo.repository;

import com.main.demo.model.entity.ClassList;
import com.main.demo.model.entity.ClassListId;
import com.main.demo.model.entity.Classroom;
import com.main.demo.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassListRepository extends JpaRepository<ClassList, ClassListId> {
    List<ClassList> findByClassroom(Classroom classroom);
    List<ClassList> findByStudent(Student student);
    void deleteByClassroom(Classroom classroom);
} 