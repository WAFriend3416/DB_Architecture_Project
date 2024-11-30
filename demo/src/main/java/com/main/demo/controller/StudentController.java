package com.main.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.main.demo.model.entity.Student;
import com.main.demo.repository.StudentRepository;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * 학생 목록 페이지를 표시합니다.
     * StudentController의 getStudents 메서드를 호출합니다.
     * @param model Model 객체
     * @return students.html 뷰를 반환
     */
    @GetMapping("/students")
    public String students(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "students";
    }

    /**
     * 학생 출결 관리 페이지를 표시합니다.
     * @return student-attendance.html 뷰를 반환
     */
    @GetMapping("/student-attendance")
    public String studentAttendance() {
        return "student-attendance";
    }
    
    /**
     * 학생 결제 관리 페이지를 표시합니다.
     * @return student-payment.html 뷰를 반환
     */
    @GetMapping("/student-payment")
    public String studentPayment() {
        return "student-payment";
    }

    /**
     * 학생 목록 데이터를 JSON 형태로 반환합니다.
     * 비동기 요청시 사용되는 API 엔드포인트입니다.
     * @return 학생 목록 데이터
     */
    @GetMapping("/api/students/refresh")
    @ResponseBody
    public List<Student> refreshStudents() {
        return studentRepository.findAll();
    }

    /**
     * 새로운 학생을 등록합니다.
     * @param student 등록할 학생 정보
     * @return 저장된 학생 정보와 상태 코드
     */
    @PostMapping("/api/students")
    @ResponseBody
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        try {
            // 받은 데이터 상세 로깅
            
            // null 체크 전에 trim()을 호출하면 NullPointerException이 발생할 수 있으므로 수정
            if (student.getSCode() == null || 
                student.getSName() == null || 
                student.getSAge() <= 0 ||
                student.getSContact() == null || 
                student.getSeContact() == null || 
                student.getSCSchool() == null) {
                
                return ResponseEntity
                    .badRequest()
                    .body("필수 항목이 누락되었습니다.");
            }

            // 빈 문자열 체크는 null 체크 후에 수행
            if (student.getSCode().trim().isEmpty() || 
                student.getSName().trim().isEmpty() || 
                student.getSContact().trim().isEmpty() || 
                student.getSeContact().trim().isEmpty() || 
                student.getSCSchool().trim().isEmpty()) {
                
                return ResponseEntity
                    .badRequest()
                    .body("필수 항목에 빈 값이 포함되어 있습니다.");
            }

            // 학생 코드 중복 검사
            if (studentRepository.findById(student.getSCode()).isPresent()) {
                return ResponseEntity
                    .badRequest()
                    .body("이미 존재하는 학생 코드입니다.");
            }

            System.out.println("\n=== INSERT 직전 최종 데이터 ===");
            System.out.println("저장할 데이터: " + student.toString());
            System.out.println("========================\n");

            Student savedStudent = studentRepository.save(student);
            return ResponseEntity.ok(savedStudent);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("서버 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 전역 예외 처리기
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("서버 오류가 발생했습니다: " + e.getMessage());
    }

    @PutMapping("/api/students/{sCode}")
    @ResponseBody
    public ResponseEntity<?> updateStudent(@PathVariable String sCode, @RequestBody Student student) {
        try {
            if (!studentRepository.existsById(sCode)) {
                return ResponseEntity.notFound().build();
            }
            student.setSCode(sCode);  // 경로의 sCode로 설정
            Student updatedStudent = studentRepository.save(student);
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("수정 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/students/{sCode}")
    @ResponseBody
    public ResponseEntity<?> deleteStudent(@PathVariable String sCode) {
        try {
            if (!studentRepository.existsById(sCode)) {
                return ResponseEntity.notFound().build();
            }
            studentRepository.deleteById(sCode);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
} 