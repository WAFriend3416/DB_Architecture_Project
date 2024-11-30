package com.main.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.main.demo.model.entity.Teacher;
import com.main.demo.repository.TeacherRepository;

import java.util.List;

@Controller
@RequestMapping
public class TeacherController {
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    @GetMapping("/teachers")
    public String teachersPage() {
        return "teachers";
    }
    
    /**
     * 선생님 분반 현황 페이지를 표시합니다.
     * @return teacher-classes.html 뷰를 반환
     */
    @GetMapping("/teacher-classes")
    public String teacherClasses() {
        return "teacher-classes";
    }
    
    /**
     * 선생님 급여 관리 페이지를 표시합니다.
     * @return teacher-salary.html 뷰를 반환
     */
    @GetMapping("/teacher-salary")
    public String teacherSalary() {
        return "teacher-salary";
    }



    @GetMapping("/api/teachers/refresh")
    @ResponseBody
    public List<Teacher> refreshTeachers() {
        return teacherRepository.findAll();
    }
    
    @GetMapping("/api/teachers")
    @ResponseBody
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }
    
    @PostMapping("/api/teachers")
    @ResponseBody
    public ResponseEntity<?> addTeacher(@RequestBody Teacher teacher) {
        try {
            Teacher savedTeacher = teacherRepository.save(teacher);
            return ResponseEntity.ok(savedTeacher);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("선생님 추가 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    @PutMapping("/api/teachers/{tCode}")
    @ResponseBody
    public ResponseEntity<?> updateTeacher(@PathVariable String tCode, @RequestBody Teacher teacher) {
        try {
            if (!teacherRepository.existsById(tCode)) {
                return ResponseEntity.notFound().build();
            }
            teacher.setTCode(tCode); // 안전을 위해 경로의 tCode를 설정
            Teacher updatedTeacher = teacherRepository.save(teacher);
            return ResponseEntity.ok(updatedTeacher);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("선생님 정보 수정 중 오류가 발생했습니다: " + e.getMessage());
        }
    }


    @DeleteMapping("/api/teachers/{tCode}")
    @ResponseBody
    public ResponseEntity<?> deleteTeacher(@PathVariable String tCode) {
        try {
            if (!teacherRepository.existsById(tCode)) {
                return ResponseEntity.notFound().build();
            }
            teacherRepository.deleteById(tCode);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("선생님 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
} 