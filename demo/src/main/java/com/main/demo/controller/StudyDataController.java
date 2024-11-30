package com.main.demo.controller;

import com.main.demo.model.entity.StudyData;
import com.main.demo.repository.StudyDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study-data")
public class StudyDataController {
    
    private final StudyDataRepository studyDataRepository;

    @GetMapping("/student/{studentCode}")
    public ResponseEntity<List<StudyData>> getStudentData(@PathVariable String studentCode) {
        List<StudyData> studyDataList = studyDataRepository.findStudentDataOrderByDate(studentCode);
        return ResponseEntity.ok(studyDataList);
    }
} 