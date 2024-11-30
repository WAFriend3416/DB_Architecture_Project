package com.main.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.lang.NonNull;
import java.util.List;
import com.main.demo.model.entity.Classroom;
@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, String> {
    @NonNull
    List<Classroom> findAll();
} 