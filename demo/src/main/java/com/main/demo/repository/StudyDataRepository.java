package com.main.demo.repository;

import com.main.demo.model.entity.StudyData;
import com.main.demo.model.entity.StudyDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudyDataRepository extends JpaRepository<StudyData, StudyDataId> {
    @Query("SELECT sd FROM StudyData sd WHERE sd.student.sCode = :studentCode ORDER BY sd.id.date_column DESC")
    List<StudyData> findStudentDataOrderByDate(@Param("studentCode") String studentCode);
} 