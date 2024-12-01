# My4Cut

**My4Cut**은 사용자, 포토 프레임, 기여를 원활하게 관리할 수 있도록 설계된 강력하고 확장 가능한 애플리케이션입니다. Kotlin 기반의 Spring Boot 백엔드, PostgreSQL 데이터베이스, SwiftUI 프론트엔드를 활용하여 데이터 무결성과 성능을 강화한 현대적이고 효율적인 사용자 및 콘텐츠 관리 솔루션을 제공합니다.

> **주의**: 이 레포지토리는 과제 제출용으로 비밀키는 제거된 상태로 공개되어 있습니다. 실제 운영 환경에서는 비밀키 및 민감한 정보가 포함되지 않도록 주의해 주세요.

## 목차

- [특징](#특징)
- [사용 기술](#사용-기술)
  - [백엔드](#백엔드)
  - [프론트엔드](#프론트엔드)
- [아키텍처](#아키텍처)
- [설치](#설치)
  - [필수 조건](#필수-조건)
  - [백엔드 설정](#백엔드-설정)
  - [프론트엔드 설정](#프론트엔드-설정)
- [사용 방법](#사용-방법)
- [데이터베이스 스키마](#데이터베이스-스키마)
- [기여 방법](#기여-방법)
- [라이선스](#라이선스)
- [문의](#문의)

## 특징

- **사용자 관리**: 사용자 등록, 인증(소셜 로그인 포함), 프로필 관리 기능 제공.
- **포토 프레임 관리**: 포토 프레임의 생성, 조회, 수정, 삭제 기능과 상세 메타데이터 관리.
- **기여 관리**: 관리자가 사용자 기여를 승인 및 관리할 수 있는 기능.
- **데이터 무결성**: 정규화를 통해 데이터 일관성을 유지하고 중복을 최소화.
- **성능 최적화**: 인덱싱, 캐싱, 효율적인 쿼리 전략을 통해 최적의 성능 제공.
- **확장 가능한 아키텍처**: 증가하는 사용자 수와 데이터에도 유연하게 대응 가능한 설계.

## 사용 기술

### 백엔드

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=maven&logoColor=white)

- **프로그래밍 언어**: Kotlin
- **프레임워크**: Spring Boot
- **데이터베이스**: PostgreSQL
- **ORM 도구**: Hibernate (Spring Data JPA)
- **빌드 도구**: Maven
- **버전 관리**: Git (GitHub)
- **IDE**: IntelliJ IDEA

### 프론트엔드

![Swift](https://img.shields.io/badge/Swift-F05138?style=for-the-badge&logo=swift&logoColor=white)
![SwiftUI](https://img.shields.io/badge/SwiftUI-FA7343?style=for-the-badge&logo=swiftui&logoColor=white)
![Xcode](https://img.shields.io/badge/Xcode-1575F6?style=for-the-badge&logo=xcode&logoColor=white)

- **프로그래밍 언어**: Swift
- **프레임워크**: SwiftUI
- **IDE**: Xcode

## 아키텍처

My4Cut은 **클린 아키텍처(Clean Architecture)** 원칙을 따르며, 관심사의 분리를 통해 유지보수성과 확장성을 높였습니다. 아키텍처는 다음과 같은 계층으로 구성됩니다:

1. **엔티티(Entity) 계층**: 핵심 비즈니스 모델(`MemberEntity`, `PhotoFrameEntity`)을 포함.
2. **유스케이스(Usecase) 계층**: 애플리케이션의 특정 기능을 구현(`AdminContributionUseCase` 등).
3. **인터페이스 어댑터(Interface Adapter) 계층**: 데이터 전송 객체(DTO), 컨트롤러, 레포지토리를 포함하여 외부와의 데이터 변환을 담당.
4. **프레임워크 및 드라이버(Frameworks & Drivers) 계층**: Spring Boot, PostgreSQL, SwiftUI 등 외부 도구와 프레임워크를 포함.


## 설치

### 필수 조건

- **Java Development Kit (JDK)**: 버전 11 이상
- **PostgreSQL**: 설치 및 실행
- **Maven**: 백엔드 빌드를 위해 필요
- **IntelliJ IDEA**: Kotlin 및 Spring Boot 개발을 위해 권장
- **Xcode**: SwiftUI 프론트엔드 개발을 위해 필요
- **Git**: 버전 관리를 위해 필요

### 백엔드 설정

1. **레포지토리 클론**
    ```bash
    git clone https://github.com/yourusername/my4cut.git
    cd my4cut/backend
    ```

2. **데이터베이스 설정**
    - PostgreSQL에 `my4cut`이라는 데이터베이스 생성.
    - `application.properties` 파일을 열어 데이터베이스 자격 증명을 업데이트.
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/my4cut
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **백엔드 빌드 및 실행**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

### 프론트엔드 설정

1. **Xcode에서 프로젝트 열기**
    - 프론트엔드 디렉토리로 이동.
    - `.xcodeproj` 또는 `.xcworkspace` 파일 열기.

2. **API 엔드포인트 설정**
    - SwiftUI 프로젝트 내에서 API 기본 URL을 실행 중인 백엔드 서버로 설정.

3. **프론트엔드 빌드 및 실행**
    - 원하는 시뮬레이터 또는 디바이스 선택.
    - `Cmd + R`을 눌러 애플리케이션 빌드 및 실행.

## 사용 방법

1. **새 사용자 등록**
    - 프론트엔드 애플리케이션을 열고, 등록 화면으로 이동하여 새 계정 생성.

2. **로그인**
    - 등록된 자격 증명 또는 소셜 로그인을 사용하여 인증.

3. **포토 프레임 관리**
    - 사용자 대시보드에서 포토 프레임을 생성, 조회, 수정 또는 삭제.

4. **관리자 기여 관리**
    - 관리자 패널을 통해 사용자 기여를 승인하거나 관리.

## 데이터베이스 스키마

데이터베이스는 **제3정규형(3NF)**까지 정규화되어 데이터 무결성과 중복 최소화를 보장합니다.

### 엔티티 및 관계

- **User (유저)**
    - `user_id` (PK)
    - `email` (Unique, Not Null)
    - `nickname`
    - `isAdmin`
    - `isPremium`
    - `language`
    - `created_at` (Not Null)

- **Photo_Frame (프레임)**
    - `frame_id` (PK)
    - `title`
    - `isPublic`
    - `description`
    - `imageUrl`
    - `thumbnailImageUrl`
    - `downloadCount` (Check >= 0)
    - `viewCount` (Check >= 0)
    - `makerName`
    - `created_at` (Not Null)

- **Frame_Keyword (프레임 키워드)**
    - `keyword_id` (PK)
    - `keyword` (Unique, Not Null)

- **Member_Photo_Frame (유저-프레임 매핑)**
    - `user_id` (FK to User)
    - `frame_id` (FK to Photo_Frame)
    - **Composite PK**: (`user_id`, `frame_id`)

- **Photo_Frame_Frame_Keyword (프레임-키워드 매핑)**
    - `frame_id` (FK to Photo_Frame)
    - `keyword_id` (FK to Frame_Keyword)
    - **Composite PK**: (`frame_id`, `keyword_id`)

### 제약 조건 및 인덱스

- **Primary Keys**: 각 테이블의 고유 식별자 설정.
- **Foreign Keys**: 테이블 간의 참조 무결성 유지.
- **Unique Constraints**: 중복 방지를 위해 `email`과 `keyword` 필드에 고유 제약조건 설정.
    ```sql
    ALTER TABLE User
    ADD CONSTRAINT unique_email UNIQUE (email);
    ```
- **Not Null Constraints**: 필수 입력 필드에 `NOT NULL` 제약조건 설정.
- **Check Constraints**: `Photo_Frame` 테이블의 `downloadCount`와 `viewCount` 필드는 0 이상의 값이어야 함을 제한.
    ```sql
    ALTER TABLE Photo_Frame
    ADD CONSTRAINT chk_download_count CHECK (downloadCount >= 0),
    ADD CONSTRAINT chk_view_count CHECK (viewCount >= 0);
    ```
- **Indexes**: 자주 조회되는 컬럼에 인덱스 설정 (`email`, `isPublic`).
    ```sql
    CREATE INDEX idx_user_email ON User(email);
    CREATE INDEX idx_photo_frame_is_public ON Photo_Frame(isPublic);
    ```

## 기여 방법

기여를 환영합니다! 기여하려면 다음 단계를 따라주세요:

1. **레포지토리 포크하기**
    - 레포지토리 페이지 상단의 "Fork" 버튼 클릭.

2. **포크한 레포지토리 클론하기**
    ```bash
    git clone https://github.com/yourusername/my4cut.git
    cd my4cut
    ```

3. **새 브랜치 생성하기**
    ```bash
    git checkout -b feature/your-feature-name
    ```

4. **변경 사항 커밋하기**
    ```bash
    git commit -m "Add your detailed description of changes"
    ```

5. **포크한 레포지토리에 푸시하기**
    ```bash
    git push origin feature/your-feature-name
    ```

6. **풀 리퀘스트 생성하기**
    - 원본 레포지토리로 이동하여 "New Pull Request" 클릭.

코드가 프로젝트의 코딩 표준을 준수하고 적절한 테스트를 포함하도록 해주세요.


---

**My4Cut**을 사용해 주셔서 감사합니다! 프로젝트에 문제가 있거나 개선 사항이 있으시면 언제든지 문의하시거나 레포지토리에서 이슈를 열어주세요.


