# Todo App Copilot 지침서

## 프로젝트 아키텍처

이것은 Java 11과 Gradle로 구축된 Spring Security authentication을 가진 Spring Boot 2.7.18 web application입니다. 앱은 Thymeleaf templates을 사용한 전통적인 MVC pattern을 따릅니다.

### 주요 기술 스택
- **Java Version**: Java 11 (LTS)
- **Spring Boot**: 2.7.18
- **Database**: H2 in-memory database with JPA/Hibernate 5.6.x
- **Templates**: Layout Dialect와 Spring Security integration을 가진 Thymeleaf 3.0.x
- **Security**: form-based auth와 BCrypt password encoding을 가진 Spring Security 5.7.x
- **Build**: Java 11 toolchain을 가진 Gradle
- **Testing**: JUnit 5 (5.8.x), Mockito 4.6.x

## 코드 구조 및 패턴

### Package 구성
```
com.keon.todoapp/
├── config/          # SecurityConfig.java - security 설정
├── controller/      # MVC controllers (App, Todo, User)
├── model/          # JPA entities (User, Todo)
├── repository/     # Spring Data JPA repositories
└── service/        # Business logic layer
```

### Authentication 흐름
- `User` entity는 Spring Security integration을 위해 `UserDetails`를 구현합니다
- `UserService`는 custom authentication을 위해 `UserDetailsService`를 구현합니다
- Security config는 `/`, `/login`, `/register`, `/about` 및 static resources에 대한 public access를 허용합니다
- 모든 `/user/**` routes는 authentication을 요구합니다

### 데이터베이스
- 이 프로젝트는 H2 인메모리 데이터베이스를 사용하며, JPA/Hibernate를 통해 ORM 매핑이 이루어집니다.
- `Todo`는 `User`와 `@ManyToOne` relationship을 가집니다
- `User`는 BCrypt hashed passwords를 저장합니다
- 두 entities 모두 getters/setters를 위해 Lombok의 `@Data`를 사용합니다

- `User` 엔티티
    - `@Entity` 어노테이션으로 JPA 엔티티로 선언됨.
    - 필드:
    - `id`: PK, 자동 생성
    - `username`: 유니크, not null
    - `password`: BCrypt 해시 저장
- Spring Security 연동을 위해 `UserDetails`를 구현함.
- 권한은 기본적으로 `"ROLE_USER"`로 반환됨.

- `Todo` 엔티티
    - `@Entity`, `@Table(name = "todos")`로 선언됨.
    - 필드:
    - `id`: PK, 자동 생성
    - `category`, `title`, `description`, `priority`, `completed`
    - `createdDate`, `dueDate`: `@DateTimeFormat(pattern = "yyyy-MM-dd")` 적용
    - `user`: `@ManyToOne`으로 `User`와 관계 설정

- 관계 및 제약사항
    - `Todo`는 여러 개가 하나의 `User`에 속함 (`@ManyToOne`)
    - 모든 엔티티는 Lombok의 `@Data`를 사용하여 getter/setter 자동 생성
    - DB 테이블명은 `todos` (명시적 지정), `user`는 기본값 사용

## 개발 패턴

### 기본 스타일

- 모든 클래스, 메서드, 필드에 명확한 접근제어자(`public`, `private`, `protected`)를 사용합니다.
- 클래스, 메서드, 변수명은 카멜케이스(CamelCase)로 작성합니다.
- 불필요한 주석은 지양하고, 핵심 로직이나 예외 처리, API 설명에는 Javadoc을 적극적으로 사용합니다.

### 패키지 및 클래스 구조

- 패키지는 역할별로 분리합니다: `config`, `controller`, `model`, `repository`, `service`
- 엔티티 클래스는 `model` 패키지에 위치하며, JPA 어노테이션(`@Entity`, `@Table`, `@Id`, `@GeneratedValue` 등)을 명확히 사용합니다.
- 비즈니스 로직은 `service` 패키지에 위치하며, `@Service` 어노테이션을 사용합니다.
- 데이터 접근은 `repository` 패키지에서 `JpaRepository`를 상속하여 구현합니다.
- 웹 요청 처리는 `controller` 패키지에서 담당하며, `@Controller` 또는 `@RestController`를 사용합니다.

### Controller 규칙
- MVC 컨트롤러는 `@Controller`를 사용하며, 뷰 이름을 반환합니다.
- REST APIs를 위한 `@RestController` (JSON을 반환)
- 인증된 사용자 정보는 `Authentication` 파라미터를 통해 얻고, `userService.findByUsername(authentication.getName())`으로 조회합니다.
- URL 패턴은 `/user/todos`, `/users/{id}` 등 역할별로 명확하게 구분합니다
- authenticated todo operations를 위한 Path pattern: `/user/todos`

### Spring Security 5.7.x 패턴 (Java 11 호환)

- `WebSecurityConfigurerAdapter`를 상속하여 SecurityConfig를 구현합니다.
- `configure(HttpSecurity http)` 메서드를 오버라이드하여 보안 설정을 구성합니다.
- `PasswordEncoder`는 `BCryptPasswordEncoder`를 사용하며 `@Bean`으로 등록합니다.
- 인증 관리자는 `configure(AuthenticationManagerBuilder auth)` 메서드에서 구성합니다.

### 주요 코드 패턴

- Lombok의 `@Data`를 사용하여 getter/setter, equals, hashCode, toString을 자동 생성합니다.
- 엔티티 간 관계는 JPA 어노테이션(`@ManyToOne`, `@OneToMany` 등)으로 명확히 지정합니다.
- 날짜 필드는 항상 `@DateTimeFormat(pattern = "yyyy-MM-dd")`을 사용합니다.
- 인증 및 권한 처리는 Spring Security와 연동하며, `User` 엔티티는 `UserDetails`를 구현합니다.
- 비밀번호는 반드시 BCrypt로 해시 처리하여 저장합니다.

### 예외 처리

- 데이터 조회 시 존재하지 않는 경우 `RuntimeException` 또는 `UsernameNotFoundException`을 명확히 throw합니다.
- 서비스/레포지토리 계층에서 예외 발생 시, 적절한 메시지를 포함하여 예외를 던집니다.

### Template 구조
- Thymeleaf fragments를 가진 `templates/layout.html`의 Base layout
- Bootstrap 5.1.3과 Font Awesome 6.0.0 CDN을 사용합니다 (Java 11 호환)
- 조건부 content를 위한 Security expressions: `sec:authorize="isAuthenticated()"`
- `templates/user/` directory의 User-specific templates

### Data 처리
- forms의 date fields를 위한 `@DateTimeFormat(pattern = "yyyy-MM-dd")`
- H2 in-memory database - 애플리케이션 재시작 시 데이터 초기화됨
- `spring.jpa.hibernate.ddl-auto=create-drop`를 사용하여 스키마 자동 생성 및 삭제
- H2 Console을 개발 환경에서 활성화: `spring.h2.console.enabled=true`

### 테스트 코드
- 모든 테스트는 JUnit 5(`org.junit.jupiter`) 5.8.x 기반으로 작성합니다.
- Spring Boot Test(`@SpringBootTest`)를 사용하여 통합 테스트를 지원합니다.
- 단위 테스트에서는 Mockito 4.6.x(`@Mock`, `@InjectMocks`, `MockitoAnnotations.openMocks`)를 활용하여 의존성 객체를 mocking합니다.
- 테스트 클래스는 `src/test/java/com/keon/todoapp/` 하위에 위치합니다.

## Build 및 개발

### Gradle 의존성 버전 (Java 11 호환)
```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.0.4.RELEASE'
    runtimeOnly 'com.h2database:h2'
    compileOnly 'org.projectlombok:lombok:1.18.24'
    annotationProcessor 'org.projectlombok:lombok:1.18.24'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'
}
```

### Gradle Tasks
```bash
./gradlew bootRun          # development server 시작
./gradlew test             # tests 실행
./gradlew build            # tests와 함께 전체 build
```

### Testing 설정
- Security Test 지원과 함께 Spring Boot Test를 사용합니다
- mocking을 위한 Mockito 4.6.x
- `build/reports/tests/`에서 생성되는 Test reports

### Configuration 주의사항
- development를 위해 비활성화된 Thymeleaf caching (`spring.thymeleaf.cache=false`)
- 더 나은 reflection support를 위해 `-parameters`를 포함하는 Compiler args
- SecurityConfig에서 비활성화된 CSRF (production에서는 조정하세요)
- H2 Console 개발 접속: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:testdb)
- Java 11 호환성을 위해 `--add-opens` JVM 옵션이 필요할 수 있습니다

## 일반적인 개발 작업

새로운 features를 추가할 때:
1. JPA annotations와 함께 `model/`에서 entity를 생성하세요
2. `JpaRepository`를 extending하는 repository interface를 추가하세요
3. business logic과 함께 service layer를 구현하세요
4. 적절한 security context와 함께 controller를 생성하세요
5. layout fragments를 사용하는 Thymeleaf templates를 추가하세요
6. 새로운 routes가 다른 access rules를 필요로 한다면 SecurityConfig를 업데이트하세요

user context와 작업할 때는 항상 controllers에서 `Authentication authentication` parameter를 사용하고 현재 user를 얻기 위해 `userService.findByUsername(authentication.getName())`을 호출하세요.

## Java 11 특화 주의사항
- Record 클래스는 Java 14부터 지원되므로 사용하지 않습니다.
- Switch expressions는 Java 14부터 정식 지원되므로 전통적인 switch문을 사용합니다.
- Text blocks는 Java 15부터 지원되므로 전통적인 문자열 연결을 사용합니다.
- var 키워드는 Java 10부터 사용 가능하지만, 명시적 타입 선언을 권장합니다.
