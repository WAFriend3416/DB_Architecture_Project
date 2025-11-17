# 학생 성적 추이 그래프 조회 시스템 (Student Grade Tracking System)

## 📋 목차
- [프로젝트 개요](#-프로젝트-개요)
- [배경 및 문제 정의](#-배경-및-문제-정의)
- [솔루션](#-솔루션)
- [주요 기능](#-주요-기능)
- [기술 스택](#-기술-스택)
- [데이터베이스 설계](#-데이터베이스-설계)
- [개발 프로세스](#-개발-프로세스)
- [프로젝트 성과](#-프로젝트-성과)
- [시스템 구조](#-시스템-구조)
- [설치 및 실행](#-설치-및-실행)

---

## 📚 프로젝트 개요

학부모 상담을 위한 학생별 성적 추이 그래프를 자동으로 생성·조회할 수 있는 웹 기반 학원 관리 시스템입니다.
기존 엑셀 기반의 수작업 프로세스를 웹 시스템으로 전환하여, 상담 준비 시간을 획기적으로 단축하고 데이터 정확성을 향상시켰습니다.

### 핵심 가치
- **시간 절감**: 상담 준비 시간을 수 시간에서 **30분 이내**로 단축
- **데이터 정확성**: 3명의 선생님이 중복 입력·검증하던 프로세스 자동화로 휴먼 에러 제거
- **확장 가능한 구조**: 정규 수업과 특강을 유연하게 관리할 수 있는 N:M 관계 설계

---

## 🔍 배경 및 문제 정의

### 기존 프로세스의 문제점

학부모 상담 전 학생별 성적 추이 그래프를 준비하는 과정에서 다음과 같은 비효율이 발생했습니다:

```
[기존 프로세스]
📊 엑셀 기반 수작업
├─ 3명의 선생님이 동일 작업 중복 수행
├─ 하루 8시간 중 약 4시간을 데이터 입력·검증에 소비
├─ 학년별·특강별 시트가 혼재되어 데이터 관리 복잡
└─ 휴먼 에러 발생 가능성 상존
```

#### 구체적인 문제점

1. **비효율적인 인력 운용**
   - 3명의 선생님이 하루 4시간씩 중복 작업 (총 12시간/일)
   - 데이터 입력 → 검증 → 그래프 생성을 매번 반복

2. **복잡한 데이터 구조**
   - 학년에 상관없이 여러 특강에 참여하는 학생 관리 어려움
   - 학년별·특강별로 분리된 엑셀 시트로 인한 데이터 파편화
   - 학생별 전체 성적 추이를 파악하기 어려운 구조

3. **확장성 부족**
   - 새로운 강의 추가 시 수작업 필요
   - 데이터 히스토리 관리의 한계

> **[이미지 삽입 위치 1]**: 기존 프로세스 다이어그램
> - 3명의 선생님이 엑셀로 중복 작업하는 모습
> - 시간 흐름에 따른 작업 프로세스 (입력 → 검증 → 그래프 생성)
> - 각 단계별 소요 시간 표시

---

## 💡 솔루션

### 1. 데이터베이스 구조 재설계

**학생-강의 관계를 N:M 구조로 재설계**하여 복잡한 수강 관계를 유연하게 관리

```
Student (학생)
    ↓ N
ClassList (수강 목록) ← 중간 테이블
    ↓ M
Classroom (강의: 정규/특강 구분 없음)
```

이를 통해:
- 한 학생이 여러 강의(정규+특강) 동시 수강 가능
- 강의별·학생별 성적 추이를 일관되게 조회 가능
- 새로운 강의 추가 시에도 기존 구조 변경 불필요

### 2. 웹 기반 시스템 구축

- 실시간 데이터 조회 및 그래프 자동 생성
- 중복 입력 제거 및 데이터 일관성 보장
- 직관적인 UI를 통한 빠른 정보 접근

### 3. AI 도구 활용을 통한 개발 효율화

- **백엔드**: 도메인 로직, API 설계, 데이터베이스 쿼리에 집중
- **프론트엔드**: Cursor가 생성한 화면·그래프 코드를 기준으로 필요한 부분만 선별·수정
- 반복적인 CRUD 작업과 UI 코드 작성 부담 최소화

> **[이미지 삽입 위치 2]**: 개선된 프로세스 다이어그램
> - 웹 시스템 도입 후 프로세스 (데이터 입력 1회 → 자동 그래프 생성)
> - Before/After 비교 차트 (12시간 → 30분)
> - 휴먼 에러 감소 효과 시각화

---

## ⭐ 주요 기능

### 1. 학생 관리 (`/students`)
- 학생 정보 등록·조회·수정·삭제
- 학생별 수강 강의 목록 조회
- 학교 정보 (초·중·고) 관리

### 2. 강의(분반) 관리 (`/classes`)
- 정규 수업 및 특강 통합 관리
- 강의별 담당 교사 지정 (메인/서브)
- 강의별 수강생 목록 조회

### 3. 학생-강의 연결 관리 (`/class-students`)
- N:M 관계를 통한 유연한 수강 신청 관리
- 학생별·강의별 수강 이력 조회

### 4. 성적 데이터 관리 (`/learning-data`)
- **학생별·강의별 성적 입력** (score1~4: 학기별 또는 평가회차별)
- 담당 교사 정보 연계
- 데이터 검증 및 수정 기능

### 5. **성적 추이 그래프 조회** ⭐ 핵심 기능
- **학생별 전체 강의 성적 추이 시각화**
- 강의별 필터링을 통한 세부 분석
- Chart.js 기반의 직관적인 그래프
- 학부모 상담 시 즉시 활용 가능

> **[이미지 삽입 위치 3]**: 성적 추이 그래프 스크린샷
> - 학생별 여러 강의의 성적 추이를 보여주는 라인 그래프
> - 필터링 및 조회 UI
> - 그래프 범례 및 데이터 포인트 표시

### 6. 교사 관리 (`/teachers`)
- 교사 정보 등록·조회·수정·삭제
- 담당 강의 목록 조회

### 7. 대시보드 (`/main`)
- 전체 학생 수 현황
- 수업 현황 요약
- 신규 등록 및 출석률 통계

> **[이미지 삽입 위치 4]**: 대시보드 스크린샷
> - 주요 지표 카드 (학생 수, 수업 수, 출석률 등)
> - 최근 등록 학생 목록
> - 요약 통계 그래프

---

## 🛠 기술 스택

### Backend
| 기술 | 버전 | 용도 |
|------|------|------|
| Java | 17+ | 백엔드 언어 |
| Spring Boot | 3.x | 애플리케이션 프레임워크 |
| Spring Data JPA | 3.x | ORM 및 데이터 액세스 |
| MySQL | 8.0+ | 관계형 데이터베이스 |
| Lombok | - | 보일러플레이트 코드 감소 |

### Frontend
| 기술 | 용도 |
|------|------|
| Thymeleaf | 서버 사이드 템플릿 엔진 |
| Bootstrap 4 | 반응형 UI 프레임워크 |
| jQuery | DOM 조작 및 AJAX |
| Chart.js | 성적 추이 그래프 시각화 |
| DataTables | 테이블 데이터 관리 |

### Development Tools
- **Cursor AI**: 프론트엔드 코드 생성 및 반복 작업 자동화
- **IntelliJ IDEA**: Java 개발 환경
- **Git/GitHub**: 버전 관리

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

> **[이미지 삽입 위치 5]**: 상세 ER 다이어그램
> - 각 테이블의 컬럼과 데이터 타입 포함
> - N:M 관계 명확히 표시
> - 외래키 관계선 표시
> - 카디널리티 표기 (1:N, N:M)

### 핵심 테이블 설명

#### 1. Student (학생)
```sql
- sCode: 학생 코드 (PK)
- sName: 학생 이름
- sAge: 나이
- sContact: 학생 연락처
- seContact: 비상 연락처
- sCSchool: 현재 학교
- sESchool: 초등학교
- sMSchool: 중학교
- sHSchool: 고등학교
```

#### 2. Classroom (강의/분반)
```sql
- cCode: 강의 코드 (PK)
- cName: 강의명 (정규/특강 구분 없음)
- tMain: 메인 교사 코드 (FK → Teacher)
- tSub: 서브 교사 코드 (FK → Teacher)
```

#### 3. ClassList (학생-강의 연결)
```sql
- sCode: 학생 코드 (PK, FK → Student)
- cCode: 강의 코드 (PK, FK → Classroom)
```
**→ N:M 관계를 표현하는 중간 테이블**

#### 4. StudyData (학습 데이터/성적)
```sql
- sCode: 학생 코드 (PK, FK → Student)
- cCode: 강의 코드 (PK, FK → Classroom)
- testNum: 시험 회차/학기 (PK)
- score1~4: 평가 점수 (Float)
- tCode: 담당 교사 코드 (FK → Teacher)
```

#### 5. Teacher (교사)
```sql
- tCode: 교사 코드 (PK)
- tName: 교사 이름
- tContact: 연락처
- tEmail: 이메일
```

### 설계 포인트

1. **N:M 관계 활용**
   - `ClassList` 테이블을 통해 학생과 강의의 다대다 관계 구현
   - 한 학생이 여러 강의(정규+특강)에 자유롭게 참여 가능

2. **복합 키 설계**
   - `StudyData`는 (sCode, cCode, testNum)을 복합 PK로 사용
   - 학생별·강의별·회차별 성적을 고유하게 식별

3. **외래키 제약 조건**
   - 데이터 무결성 보장
   - Cascade 옵션을 통한 연관 데이터 자동 관리

---

## 🚀 개발 프로세스

### 1. 문제 분석 및 요구사항 정의
- 기존 프로세스 분석 (3명 × 4시간/일 소요)
- 주요 페인 포인트 식별
- 해결 방향 제안 및 승인

### 2. 데이터베이스 설계
- 엑셀 시트 구조 분석
- 정규화 및 N:M 관계 재설계
- 성적 추이 조회에 최적화된 테이블 구조 정의

### 3. 백엔드 개발 (주력 영역)
- **Spring Boot 기반 REST API 구현**
- **JPA Entity 및 Repository 설계**
- **도메인 로직 및 비즈니스 규칙 구현**
- **효율적인 쿼리 작성** (N+1 문제 해결, JOIN 최적화)

### 4. 프론트엔드 개발 (AI 도구 활용)
- Cursor AI로 기본 화면 및 그래프 코드 생성
- 생성된 코드 중 **설계 의도에 부합하는 부분만 선별**
- 가독성 및 유지보수성 개선을 위한 수정 적용
- **프론트엔드 개발 시간을 대폭 단축**

### 5. 테스트 및 피드백
- 원장 및 교사진 대상 시연
- 실제 상담 시나리오 검증
- 사용성 개선 사항 반영

### 6. 배포 및 운영
- 1개월 내 MVP 완성
- 실제 학부모 상담에 활용
- 지속적인 피드백 수집 및 개선

---

## 📈 프로젝트 성과

### 1. 업무 효율성 향상

| 항목 | 기존 (Before) | 개선 (After) | 효과 |
|------|--------------|-------------|------|
| **상담 준비 시간** | 수 시간 | **약 30분** | ⬇️ **90% 단축** |
| **인력 투입** | 3명 × 4시간/일 | 데이터 입력 시 1회만 | ⬇️ **중복 작업 제거** |
| **데이터 정확성** | 수작업 오류 가능 | 시스템 자동 검증 | ⬆️ **정확도 향상** |
| **그래프 생성** | 매번 수작업 | 클릭 한 번으로 자동 생성 | ⬆️ **즉시 조회** |

> **[이미지 삽입 위치 6]**: 성과 비교 인포그래픽
> - 시간 절감 효과를 나타내는 막대 그래프
> - 인력 투입 시간 비교 (Before: 12시간 vs After: 0.5시간)
> - ROI 계산 (시간 절감 × 인건비)

### 2. 비즈니스 가치

- **✅ 1개월 내 MVP 완성**: 빠른 개발 및 검증
- **✅ 실제 업무 적용**: 학부모 상담에 즉시 활용
- **✅ 긍정적 피드백**: 원장 및 교사진의 높은 만족도
- **✅ 확장 가능성**: 향후 기능 추가 용이한 구조

### 3. 기술적 성과

- **데이터베이스 구조 최적화**: N:M 관계 설계로 복잡한 도메인 해결
- **백엔드 역량 강화**: Spring Boot, JPA 기반의 견고한 시스템 구축
- **AI 도구 활용**: Cursor를 활용한 효율적인 개발 프로세스 확립
- **풀스택 경험**: 백엔드부터 프론트엔드까지 전체 시스템 구현

---

## 🏗 시스템 구조

### 프로젝트 디렉토리 구조

```
DB_Architecture_Project/
├── demo/
│   └── src/
│       └── main/
│           ├── java/com/main/demo/
│           │   ├── DemoApplication.java        # Spring Boot 메인 클래스
│           │   ├── controller/                 # REST API 컨트롤러
│           │   │   ├── MainController.java
│           │   │   ├── StudentController.java
│           │   │   ├── ClassroomController.java
│           │   │   ├── ClassStudentController.java
│           │   │   ├── StudyDataController.java
│           │   │   └── TeacherController.java
│           │   ├── model/entity/               # JPA 엔티티
│           │   │   ├── Student.java
│           │   │   ├── Classroom.java
│           │   │   ├── ClassList.java
│           │   │   ├── ClassListId.java
│           │   │   ├── StudyData.java
│           │   │   ├── StudyDataId.java
│           │   │   └── Teacher.java
│           │   └── repository/                 # JPA 레포지토리
│           │       ├── StudentRepository.java
│           │       ├── ClassroomRepository.java
│           │       ├── ClassListRepository.java
│           │       ├── StudyDataRepository.java
│           │       └── TeacherRepository.java
│           └── resources/
│               ├── application.properties      # 설정 파일
│               ├── static/                     # 정적 리소스
│               │   ├── css/
│               │   ├── js/
│               │   └── vendor/
│               │       ├── bootstrap/
│               │       ├── chart.js/
│               │       ├── datatables/
│               │       └── jquery/
│               └── templates/                  # Thymeleaf 템플릿
│                   ├── main.html
│                   ├── students.html
│                   ├── classes.html
│                   ├── class-students.html
│                   ├── learning-data.html
│                   └── teachers.html
└── README.md
```

> **[이미지 삽입 위치 7]**: 시스템 아키텍처 다이어그램
> - 3-Tier Architecture (Presentation → Business Logic → Data Access)
> - 각 레이어 간 데이터 흐름
> - 사용된 기술 스택 표시
> - MVC 패턴 구조

### 아키텍처 패턴

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

---

## 🔧 설치 및 실행

### 사전 요구사항

- **Java**: JDK 17 이상
- **MySQL**: 8.0 이상
- **Maven**: 3.6 이상 (또는 내장 Maven Wrapper 사용)

### 1. 프로젝트 클론

```bash
git clone https://github.com/WAFriend3416/DB_Architecture_Project.git
cd DB_Architecture_Project/demo
```

### 2. 데이터베이스 설정

```sql
-- MySQL에 데이터베이스 생성
CREATE DATABASE math_academy CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 사용자 생성 및 권한 부여 (선택사항)
CREATE USER 'academy_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON math_academy.* TO 'academy_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. 애플리케이션 설정

`src/main/resources/application.properties` 파일 수정:

```properties
# 데이터베이스 연결 설정
spring.datasource.url=jdbc:mysql://localhost:3306/math_academy?useSSL=false&serverTimezone=UTC
spring.datasource.username=academy_user
spring.datasource.password=your_password

# JPA 설정
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# 서버 포트
server.port=8080
```

### 4. 빌드 및 실행

```bash
# Maven Wrapper 사용 (권장)
./mvnw clean install
./mvnw spring-boot:run

# 또는 시스템 Maven 사용
mvn clean install
mvn spring-boot:run
```

### 5. 접속

브라우저에서 `http://localhost:8080` 접속

---

## 📱 주요 화면 구성

| 화면 | 경로 | 설명 |
|------|------|------|
| 대시보드 | `/main` | 전체 현황 및 주요 지표 |
| 학생 관리 | `/students` | 학생 CRUD 및 수강 이력 |
| 강의 관리 | `/classes` | 정규/특강 통합 관리 |
| 수강 관리 | `/class-students` | 학생-강의 연결 관리 |
| 성적 관리 | `/learning-data` | 성적 입력 및 조회 |
| **성적 추이 그래프** | `/learning-data?studentId={sCode}` | **학생별 성적 시각화** ⭐ |
| 교사 관리 | `/teachers` | 교사 정보 관리 |

---

## 🎯 향후 개선 계획

### 단기 목표
- [ ] 사용자 인증 및 권한 관리 (Spring Security)
- [ ] 성적 데이터 일괄 업로드 기능 (Excel/CSV import)
- [ ] 출석 관리 기능 추가
- [ ] 모바일 반응형 UI 개선

### 중기 목표
- [ ] 학부모 포털 구축 (학생 성적 조회)
- [ ] 알림 시스템 (SMS/Email)
- [ ] 학생별 맞춤 학습 분석 리포트 생성
- [ ] 데이터 백업 및 복구 기능

### 장기 목표
- [ ] AI 기반 학습 패턴 분석
- [ ] 성적 예측 모델 구축
- [ ] 다중 학원 지원 (SaaS 모델 전환)

---

## 📞 문의 및 기여

### 프로젝트 문의
- **Repository**: [WAFriend3416/DB_Architecture_Project](https://github.com/WAFriend3416/DB_Architecture_Project)
- **Issue Tracker**: GitHub Issues 활용

### 기여 방법
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 라이선스

이 프로젝트는 개인 포트폴리오 목적으로 제작되었습니다.

---

## 🙏 감사의 글

이 프로젝트는 실제 학원 현장의 문제를 해결하기 위해 시작되었으며,
원장님과 선생님들의 적극적인 피드백 덕분에 성공적으로 완성될 수 있었습니다.

특히 **데이터베이스 설계의 중요성**과 **사용자 중심의 개발**이 얼마나 중요한지 깊이 배울 수 있었습니다.

---

**Made with ❤️ by WAFriend3416**
