package com.main.demo.controller;

import com.main.demo.model.entity.*;
import com.main.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/class-students")
public class ClassStudentController {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassListRepository classListRepository;

    @GetMapping("/{cCode}/students")
    public ResponseEntity<?> getStudentsByClass(@PathVariable String cCode) {
        try {
            Classroom classroom = classroomRepository.findById(cCode)
                .orElseThrow(() -> new RuntimeException("분반을 찾을 수 없습니다."));

            List<Student> students = classListRepository.findByClassroom(classroom)
                .stream()
                .map(ClassList::getStudent)
                .collect(Collectors.toList());

            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableStudents(@PathVariable String cCode) {
        try {
            Classroom classroom = classroomRepository.findById(cCode)
                .orElseThrow(() -> new RuntimeException("분반을 찾을 수 없습니다."));

            List<Student> enrolledStudents = classListRepository.findByClassroom(classroom)
                .stream()
                .map(ClassList::getStudent)
                .collect(Collectors.toList());

            List<Student> allStudents = studentRepository.findAll();
            List<Student> availableStudents = allStudents.stream()
                .filter(student -> !enrolledStudents.contains(student))
                .collect(Collectors.toList());

            return ResponseEntity.ok(availableStudents);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{sCode}")
    @Transactional
    public ResponseEntity<?> addStudent(@PathVariable String cCode, @PathVariable String sCode) {
        try {
            ClassListId classListId = new ClassListId(cCode, sCode);
            if (classListRepository.existsById(classListId)) {
                return ResponseEntity.badRequest().body("이미 등록된 학생입니다.");
            }

            Classroom classroom = classroomRepository.findById(cCode)
                .orElseThrow(() -> new RuntimeException("분반을 찾을 수 없습니다."));

            Student student = studentRepository.findById(sCode)
                .orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다."));

            ClassList classList = new ClassList();
            classList.setId(classListId);
            classList.setClassroom(classroom);
            classList.setStudent(student);

            classListRepository.save(classList);

            return ResponseEntity.ok("학생이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{sCode}")
    @Transactional
    public ResponseEntity<?> removeStudent(@PathVariable String cCode, @PathVariable String sCode) {
        try {
            ClassListId classListId = new ClassListId(cCode, sCode);
            if (!classListRepository.existsById(classListId)) {
                return ResponseEntity.badRequest().body("등록된 학생이 없습니다.");
            }

            classListRepository.deleteById(classListId);
            return ResponseEntity.ok("학생이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{cCode}/students/{sCode}")
    public ResponseEntity<?> removeStudentFromClass(
            @PathVariable String cCode,
            @PathVariable String sCode) {
        try {
            ClassListId classListId = new ClassListId(cCode, sCode);
            classListRepository.deleteById(classListId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 