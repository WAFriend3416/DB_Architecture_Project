package com.main.demo.repository;

import com.main.demo.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.lang.NonNull;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    //학생 정보 전체 출력
    @NonNull
    List<Student> findAll();
    
    // 학생 이름으로 검색
    // List<Student> findBySNameContaining(String name);
    
    // // 나이로 검색
    // List<Student> findBySAge(int age);
    
  
    // // 학교로 검색
    // List<Student> findBySCSchool(String school);
} 