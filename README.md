# MAIS - Math Academy Information System

## 📚 프로젝트 소개

MAIS는 학원의 학생별 성적 추이를 효율적으로 관리하고, 학부모 상담을 위한 성적 그래프를 자동으로 생성하는 웹 기반 학원 관리 시스템입니다.

### 🎯 핵심 가치
- **시간 절감**: 상담 준비 시간을 수 시간에서 약 30분으로 단축
- **데이터 정확성**: 중복 입력·검증 프로세스 자동화로 휴먼 에러 제거
- **확장 가능한 구조**: N:M 관계 설계로 정규 수업과 특강을 유연하게 관리

---

## 🔍 배경 및 문제 정의

### 기존 프로세스의 문제점

학부모 상담 전 학생별 성적 추이 그래프를 매번 엑셀로 생성·검증하는 과정에서 다음과 같은 비효율이 발생했습니다:

#### 1. 엑셀 파일 공유로 인한 데이터 정합성 문제
- **3명의 선생님이 같은 엑셀 파일을 공유하면서 데이터 중복·오수정 발생**
- 데이터 불일치를 찾아 수정·검증하는 데 하루 약 4시간 소요
- 엑셀 기반 공유 파일 관리의 전형적인 문제점 (동시 편집 불가, 버전 관리 어려움)

#### 2. 복잡한 데이터 구조
- **학년에 상관없이 여러 특강에 참여하는 학생 관리의 어려움**
- 학년별·특강별로 분리된 엑셀 시트로 인한 데이터 파편화
- 학생별 전체 성적 추이 파악의 한계

#### 3. 확장성 부족
- 새로운 강의 추가 시마다 수작업 필요
- 데이터 히스토리 관리 및 추적의 어려움

---

## 🚀 해결 과정

### 1. 문제 분석 및 요구사항 정의
기존 엑셀 파일을 분석하고 현장 근무자 인터뷰를 통해 주요 페인 포인트를 식별했습니다. 3명의 선생님이 같은 파일을 공유하면서 발생하는 데이터 중복·오수정을 찾아 검증하는 데 하루 약 4시간이 소요되는 비효율적인 프로세스를 발견하고, 이를 웹 시스템으로 대체하는 방향을 제안했습니다.

### 2. 데이터베이스 설계
학년별·특강별로 혼재되어 있던 엑셀 시트 구조를 분석하여, **학생-강의 관계를 N:M 구조로 재설계**했습니다. 이를 통해 학생이 학년에 상관없이 여러 정규 수업과 특강에 자유롭게 참여할 수 있도록 하고, 학생별·강의별 성적 추이를 일관되게 조회할 수 있는 테이블 구조와 쿼리 패턴을 정의했습니다.

### 3. 백엔드 개발
Spring Boot 기반으로 도메인 로직, REST API, 효율적인 데이터베이스 쿼리 구현에 집중했습니다. JPA를 활용해 N:M 관계를 코드로 표현하고, 성적 추이 조회를 위한 최적화된 쿼리를 작성했습니다.

### 4. 프론트엔드 개발 (AI 협업)
Cursor가 생성한 화면 및 Chart.js 기반 그래프 코드를 기준으로, **설계 의도와 가독성에 맞는 부분만 선별·수정**하여 적용했습니다. 이를 통해 프론트엔드 코드 작성 부담을 줄이고 반복 개발 속도를 높였습니다.

### 5. MVP 시연 및 피드백
1개월 내 웹 기반 학생별 성적 추이 그래프 조회 MVP를 완성해 원장에게 시연하고, 실제 상담에 활용되도록 했습니다. **상담 준비 시간이 수 시간에서 약 30분 안팎으로 줄었다**는 긍정적인 피드백을 받았습니다.

---

## 💻 주요 기능

### 1. 학생 관리
- 학생 정보 등록·조회·수정·삭제
- 학생별 수강 강의 목록 조회
- 학교 정보 관리

### 2. 강의(분반) 관리
- 정규 수업 및 특강 통합 관리
- 강의별 담당 교사 지정 (메인/서브)
- 강의별 수강생 목록 조회

### 3. 수강 관리
- N:M 관계를 통한 유연한 학생-강의 연결
- 학생별·강의별 수강 이력 조회

### 4. 성적 데이터 관리
- 학생별·강의별 성적 입력 및 수정
- 담당 교사 정보 연계
- 데이터 검증 기능

### 5. 성적 추이 그래프 조회 ⭐
- **학생별 전체 강의 성적 추이 시각화**
- 강의별 필터링을 통한 세부 분석
- Chart.js 기반의 직관적인 라인 그래프
- 학부모 상담 시 즉시 활용 가능

### 6. 교사 관리
- 교사 정보 등록·조회·수정·삭제
- 담당 강의 목록 조회

---

## 🛠 기술 스택

**Backend**: Spring Boot, Spring Data JPA, MySQL
**Frontend**: Thymeleaf, Bootstrap, jQuery, Chart.js
**Development**: Cursor AI (프론트엔드 코드 생성), IntelliJ IDEA, Git/GitHub

---

## 🗄 데이터베이스 설계

### ERD (Entity Relationship Diagram)

```
┌─────────────┐         ┌──────────────┐         ┌─────────────┐
│   Student   │         │  ClassList   │         │  Classroom  │
├─────────────┤         ├──────────────┤         ├─────────────┤
│ sCode (PK)  │◄───────┤ sCode (PK,FK)│         │ cCode (PK)  │
│ sName       │         │ cCode (PK,FK)├────────►│ cName       │
│ sAge        │         └──────────────┘         │ tMain (FK)  │
│ sContact    │                                  │ tSub (FK)   │
│ seContact   │                                  └─────────────┘
│ sCSchool    │                                          │
│ sESchool    │                                          │
│ sMSchool    │         ┌──────────────┐                │
│ sHSchool    │         │  StudyData   │                │
└─────────────┘         ├──────────────┤                │
       │                │ sCode (PK,FK)├────────────────┘
       │                │ cCode (PK,FK)│
       │                │ testNum (PK) │
       └───────────────►│ score1       │
                        │ score2       │
                        │ score3       │         ┌─────────────┐
                        │ score4       │         │   Teacher   │
                        │ tCode (FK)   ├────────►├─────────────┤
                        └──────────────┘         │ tCode (PK)  │
                                                 │ tName       │
                                                 │ tContact    │
                                                 │ tEmail      │
                                                 └─────────────┘
```

> **[이미지]**: 상세 ER 다이어그램 (데이터 타입, 제약 조건 포함)

### 핵심 테이블

**Student** - 학생 기본 정보
**Classroom** - 강의/분반 정보 (정규/특강 통합)
**ClassList** - 학생-강의 N:M 관계 중간 테이블
**StudyData** - 학생별·강의별·회차별 성적 데이터
**Teacher** - 교사 정보

### 설계 핵심

1. **N:M 관계**: `ClassList`를 통해 한 학생이 여러 강의(정규+특강)에 자유롭게 참여 가능
2. **복합 키**: `StudyData`는 (sCode, cCode, testNum)으로 성적을 고유하게 식별
3. **외래키 제약**: 데이터 무결성 보장 및 연관 데이터 자동 관리

---

## 🏗 시스템 구조

### 아키텍처

```
┌─────────────────────────────────────┐
│      Presentation Layer             │
│   (Thymeleaf + Bootstrap + Chart.js)│
└──────────────┬──────────────────────┘
               │ HTTP Request/Response
┌──────────────▼──────────────────────┐
│      Business Logic Layer           │
│   (Spring Boot Controllers)         │
└──────────────┬──────────────────────┘
               │ JPA/Hibernate
┌──────────────▼──────────────────────┐
│      Data Access Layer              │
│   (Spring Data JPA Repositories)    │
└──────────────┬──────────────────────┘
               │ JDBC
┌──────────────▼──────────────────────┐
│      Database (MySQL)               │
└─────────────────────────────────────┘
```

> **[이미지]**: 시스템 아키텍처 다이어그램 (기술 스택 포함)

### 프로젝트 구조

```
demo/
└── src/main/
    ├── java/com/main/demo/
    │   ├── DemoApplication.java
    │   ├── controller/           # REST API 컨트롤러
    │   │   ├── StudentController.java
    │   │   ├── ClassroomController.java
    │   │   ├── ClassStudentController.java
    │   │   ├── StudyDataController.java
    │   │   └── TeacherController.java
    │   ├── model/entity/         # JPA 엔티티
    │   │   ├── Student.java
    │   │   ├── Classroom.java
    │   │   ├── ClassList.java
    │   │   ├── StudyData.java
    │   │   └── Teacher.java
    │   └── repository/           # JPA 레포지토리
    │       ├── StudentRepository.java
    │       ├── ClassroomRepository.java
    │       ├── ClassListRepository.java
    │       ├── StudyDataRepository.java
    │       └── TeacherRepository.java
    └── resources/
        ├── application.properties
        ├── static/               # CSS, JS, Vendor libraries
        └── templates/            # Thymeleaf 템플릿
```

---

## 📈 프로젝트 성과

### 업무 효율성 향상

| 항목 | 기존 (Before) | 개선 (After) | 효과 |
|------|--------------|-------------|------|
| **상담 준비 시간** | 수 시간 | 약 30분 | ⬇️ 90% 단축 |
| **데이터 검증 시간** | 약 4시간/일 | 실시간 자동 검증 | ⬇️ 검증 작업 제거 |
| **데이터 정확성** | 공유 파일 오류 빈번 | 시스템 자동 검증 | ⬆️ 정합성 보장 |
| **그래프 생성** | 매번 수작업 | 클릭 한 번으로 자동 생성 | ⬆️ 즉시 조회 |

### 비즈니스 가치

- ✅ **1개월 내 MVP 완성**: 빠른 개발 및 검증
- ✅ **실제 업무 적용**: 학부모 상담에 즉시 활용
- ✅ **긍정적 피드백**: 원장 및 교사진의 높은 만족도
- ✅ **확장 가능성**: 향후 기능 추가 용이한 구조

### 기술적 성과

- **데이터베이스 구조 최적화**: N:M 관계 설계로 복잡한 도메인 해결
- **백엔드 역량 강화**: Spring Boot, JPA 기반의 견고한 시스템 구축
- **AI 도구 활용**: Cursor를 활용한 효율적인 개발 프로세스 확립
- **풀스택 경험**: 백엔드부터 프론트엔드까지 전체 시스템 구현

---

## 🔧 설치 및 실행

### 사전 요구사항
- Java JDK 17 이상
- MySQL 8.0 이상
- Maven 3.6 이상

### 실행 방법

```bash
# 1. 레포지토리 클론
git clone https://github.com/WAFriend3416/DB_Architecture_Project.git
cd DB_Architecture_Project/demo

# 2. MySQL 데이터베이스 생성
mysql -u root -p
CREATE DATABASE math_academy CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 3. application.properties 설정
# src/main/resources/application.properties 파일에서 DB 연결 정보 수정

# 4. 애플리케이션 실행
./mvnw spring-boot:run

# 5. 브라우저 접속
# http://localhost:8080
```

---

## 📱 주요 화면

| 화면 | 경로 | 설명 |
|------|------|------|
| 학생 관리 | `/students` | 학생 CRUD 및 수강 이력 |
| 강의 관리 | `/classes` | 정규/특강 통합 관리 |
| 수강 관리 | `/class-students` | 학생-강의 연결 관리 |
| 성적 관리 | `/learning-data` | 성적 입력 및 조회 |
| **성적 추이 그래프** | `/learning-data?studentId={sCode}` | **학생별 성적 시각화** ⭐ |
| 교사 관리 | `/teachers` | 교사 정보 관리 |

---

## 📞 문의

- **Repository**: [WAFriend3416/DB_Architecture_Project](https://github.com/WAFriend3416/DB_Architecture_Project)
- **Issue Tracker**: GitHub Issues

---

**Made with ❤️ by WAFriend3416**
