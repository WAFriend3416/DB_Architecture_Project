package com.main.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.main.demo.model.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    // 기본적인 CRUD 메서드는 JpaRepository에서 제공됩니다.
    
    // 추가적인 쿼리 메서드가 필요한 경우 여기에 선언할 수 있습니다.
    // 예시:
    // List<Teacher> findByTGrade(int tGrade);
    // Optional<Teacher> findByTCode(String tCode);
    // boolean existsByTCode(String tCode);
} 