---
applyTo: "**/*.sqlite3. *.sql"
---

# 데이터베이스 설계 및 코드 생성 지침

## 프로젝트 데이터베이스 개요

- 이 프로젝트는 SQLite를 사용하며, JPA/Hibernate를 통해 ORM 매핑이 이루어집니다.
- 주요 엔티티는 `User`와 `Todo`입니다.

## 주요 엔티티 구조

### `User` 엔티티
- `@Entity` 어노테이션으로 JPA 엔티티로 선언됨.
- 필드:
  - `id`: PK, 자동 생성
  - `username`: 유니크, not null
  - `password`: BCrypt 해시 저장
- Spring Security 연동을 위해 `UserDetails`를 구현함.
- 권한은 기본적으로 `"ROLE_USER"`로 반환됨.

### `Todo` 엔티티
- `@Entity`, `@Table(name = "todos")`로 선언됨.
- 필드:
  - `id`: PK, 자동 생성
  - `category`, `title`, `description`, `priority`, `completed`
  - `createdDate`, `dueDate`: `@DateTimeFormat(pattern = "yyyy-MM-dd")` 적용
  - `user`: `@ManyToOne`으로 `User`와 관계 설정

## 관계 및 제약사항
- `Todo`는 여러 개가 하나의 `User`에 속함 (`@ManyToOne`)
- 모든 엔티티는 Lombok의 `@Data`를 사용하여 getter/setter 자동 생성
- DB 테이블명은 `todos` (명시적 지정), `user`는 기본값 사용

## 데이터 처리 및 패턴
- 날짜 필드는 항상 `"yyyy-MM-dd"` 포맷을 사용
- User의 인증 및 권한 처리는 Spring Security와 연동됨
- User의 password는 반드시 해시 처리하여 저장

## 코드 생성/리뷰 시 유의사항
- 엔티티 생성 시 JPA 어노테이션을 정확히 사용
- 관계 설정 시 `@ManyToOne`, `@OneToMany` 등 명확히 지정
- DB 변경 시 마이그레이션 및 DDL 자동 갱신(`spring.jpa.hibernate.ddl-auto=update`)
- User와 Todo의 관계, 필드 제약조건(유니크, not null 등)을 반드시 반영

## 예시 파일
- User.java
- Todo.java

