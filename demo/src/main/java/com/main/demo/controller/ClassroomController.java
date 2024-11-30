package com.main.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.main.demo.model.entity.Classroom;
import com.main.demo.repository.ClassroomRepository;

import java.util.List;

@Controller
@RequestMapping
public class ClassroomController {
    
    @Autowired
    private ClassroomRepository classroomRepository;
    
    @GetMapping("/classes")
    public String classesPage() {
        return "classes";
    }
    
    /**
     * 수업별 학생 관리 페이지를 표시합니다.
     * @return class-students.html 뷰를 반환
     */
    @GetMapping("/class-students")
    public String classStudents() {
        return "class-students";
    }

    @GetMapping("/api/classes/refresh")
    @ResponseBody
    public List<Classroom> refreshClasses() {
        return classroomRepository.findAll();
    }
    
    @PostMapping("/api/classes")
    @ResponseBody
    public ResponseEntity<?> addClass(@RequestBody Classroom classroom) {
        try {
            // 필수 필드 검증
            if (classroom.getCCode() == null || 
                classroom.getCName() == null || 
                classroom.getMainTeacher() == null) {
                
                return ResponseEntity
                    .badRequest()
                    .body("필수 항목이 누락되었습니다.");
            }

            // 빈 문자열 체크
            if (classroom.getCCode().trim().isEmpty() || 
                classroom.getCName().trim().isEmpty()) {
                
                return ResponseEntity
                    .badRequest()
                    .body("필수 항목에 빈 값이 포함되어 있습니다.");
            }

            // 분반 코드 중복 검사
            if (classroomRepository.findById(classroom.getCCode()).isPresent()) {
                return ResponseEntity
                    .badRequest()
                    .body("이미 존재하는 분반 코드입니다.");
            }

            Classroom savedClassroom = classroomRepository.save(classroom);
            return ResponseEntity.ok(savedClassroom);
            
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("분반 추가 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    @PutMapping("/api/classes/{cCode}")
    @ResponseBody
    public ResponseEntity<?> updateClass(@PathVariable String cCode, @RequestBody Classroom classroom) {
        try {
            if (!classroomRepository.existsById(cCode)) {
                return ResponseEntity.notFound().build();
            }
            classroom.setCCode(cCode); // 안전을 위해 경로의 cCode를 설정
            Classroom updatedClassroom = classroomRepository.save(classroom);
            return ResponseEntity.ok(updatedClassroom);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("분반 정보 수정 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/classes/{cCode}")
    @ResponseBody
    public ResponseEntity<?> deleteClass(@PathVariable String cCode) {
        try {
            if (!classroomRepository.existsById(cCode)) {
                return ResponseEntity.notFound().build();
            }
            classroomRepository.deleteById(cCode);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("분반 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
} 