package com.main.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    @GetMapping("/main")
    public String mainPage() {
        System.out.println("MainController.mainPage() 호출됨");
        return "blank";
    }
}