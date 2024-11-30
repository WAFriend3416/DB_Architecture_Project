# Math Academy Information System (MAIS)

MAIS는 수학 학원을 위한 종합 관리 시스템입니다. 학생, 교사, 수업, 학습 데이터를 효율적으로 관리할 수 있는 웹 기반 솔루션입니다.

## 기술 스택

### Backend
- Java 8+
- Spring Boot
- Spring MVC
- Spring Data JPA
- MySQL

### Frontend
- Thymeleaf
- Bootstrap 4
- jQuery
- DataTables
- Chart.js
- Font Awesome

## 주요 기능

### 1. 대시보드 (`/main`) - (미구현)
- 전체 학생 수 현황
- 진행중인 수업 현황
- 신규 등록 현황
- 출석률 통계
- 시스템 소개
- 공지사항

### 2. 학생 관리
- 학생 목록 조회/추가/수정/삭제 (`/students`)

### 3. 분반 관리
- 분반 목록 조회/추가/수정/삭제 (`/classes`)
- 분반별 학생 관리 (`/class-students`)

### 4. 선생님 관리
- 선생님 목록 조회/추가/수정/삭제 (`/teachers`)

### 5. 학습 데이터 관리
- 학습 데이터 조회 (`/learning-data`)
- 학습 데이터 등록 (`/learning-data/register`)
- 학생별 성적 추이 그래프

### 6. 환경설정
- 시스템 설정 관리 (`/settings`) - (미구현)

