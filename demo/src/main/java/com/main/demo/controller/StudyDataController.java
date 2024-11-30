package com.main.demo.controller;

import com.main.demo.model.entity.*;
import com.main.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study-data")
public class StudyDataController {
    
    private final StudyDataRepository studyDataRepository;
    private final StudentRepository studentRepository;
    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;

    @GetMapping("/student/{studentCode}")
    public ResponseEntity<List<StudyData>> getStudentData(@PathVariable String studentCode) {
        List<StudyData> studyDataList = studyDataRepository.findStudentDataOrderByDate(studentCode);
        return ResponseEntity.ok(studyDataList);
    }

    @PostMapping("/batch")
    public ResponseEntity<?> saveBatchStudyData(@RequestBody List<Map<String, Object>> dataList) {
        try {
            List<StudyData> studyDataList = new ArrayList<>();
            
            for (Map<String, Object> data : dataList) {
                Student student = studentRepository.findById((String) data.get("studentCode"))
                    .orElseThrow(() -> new RuntimeException("Student not found"));
                
                Classroom classroom = classroomRepository.findById((String) data.get("classCode"))
                    .orElseThrow(() -> new RuntimeException("Class not found"));
                
                // 현재 로그인한 교사 정보 가져오기 (보안 컨텍스트에서)
                // 임시로 첫 번째 교사를 가져오는 것으로 구현
                Teacher teacher = teacherRepository.findAll().get(0);
                
                StudyData studyData = new StudyData();
                StudyDataId id = new StudyDataId();
                id.setDate_column(java.time.LocalDate.parse((String) data.get("dateColumn")));
                id.setSCode(student.getSCode());
                id.setCCode(classroom.getCCode());
                
                studyData.setId(id);
                studyData.setStudent(student);
                studyData.setClassroom(classroom);
                studyData.setTeacher(teacher);
                
                // Float으로 점수 설정
                studyData.setScore1(parseFloat(data.get("score1")));
                studyData.setScore2(parseFloat(data.get("score2")));
                studyData.setScore3(parseFloat(data.get("score3")));
                studyData.setScore4(parseFloat(data.get("score4")));
                
                studyDataList.add(studyData);
            }
            
            studyDataRepository.saveAll(studyDataList);
            return ResponseEntity.ok().build();
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    private Float parseFloat(Object value) {
        if (value == null || value.toString().trim().isEmpty()) {
            return null;
        }
        try {
            return Float.parseFloat(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }
} 