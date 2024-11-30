package com.main.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.main.demo.controller.StudentController;

@Controller
public class MainController {
    
    /**
     * 메인 대시보드 페이지를 표시합니다.
     * @return dashboard.html 뷰를 반환
     */
    @GetMapping("/main")
    public String mainPage() {
        return "dashboard";
    }
    
    /**
     * 학습 데이터 목록 페이지를 표시합니다.
     * @return learning-data.html 뷰를 반환
     */
    @GetMapping("/learning-data")
    public String learningData() {
        return "learning-data";
    }
    
    /**
     * 학습 데이터 등록 페이지를 표시합니다.
     * @return learning-data-register.html 뷰를 반환
     */
    @GetMapping("/learning-data/register")
    public String learningDataRegister() {
        return "learning-data-register";
    }
    
    /**
     * 환경설정 페이지를 표시합니다.
     * @return settings.html 뷰를 반환
     */
    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }
}