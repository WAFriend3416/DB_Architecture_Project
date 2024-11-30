package com.main.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.main.demo.entity.Student;
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
    public String students() {
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
} 