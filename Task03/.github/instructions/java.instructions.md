---
applyTo: "**/*.java"
---
# Java 코드 스타일 및 패턴 지침

## 기본 스타일

- 모든 클래스, 메서드, 필드에 명확한 접근제어자(`public`, `private`, `protected`)를 사용합니다.
- 클래스, 메서드, 변수명은 카멜케이스(CamelCase)로 작성합니다.
- 불필요한 주석은 지양하고, 핵심 로직이나 예외 처리, API 설명에는 Javadoc을 적극적으로 사용합니다.

## 패키지 및 클래스 구조

- 패키지는 역할별로 분리합니다: `config`, `controller`, `model`, `repository`, `service`
- 엔티티 클래스는 `model` 패키지에 위치하며, JPA 어노테이션(`@Entity`, `@Table`, `@Id`, `@GeneratedValue` 등)을 명확히 사용합니다.
- 비즈니스 로직은 `service` 패키지에 위치하며, `@Service` 어노테이션을 사용합니다.
- 데이터 접근은 `repository` 패키지에서 `JpaRepository`를 상속하여 구현합니다.
- 웹 요청 처리는 `controller` 패키지에서 담당하며, `@Controller` 또는 `@RestController`를 사용합니다.

## 주요 코드 패턴

- Lombok의 `@Data`를 사용하여 getter/setter, equals, hashCode, toString을 자동 생성합니다.
- 엔티티 간 관계는 JPA 어노테이션(`@ManyToOne`, `@OneToMany` 등)으로 명확히 지정합니다.
- 날짜 필드는 항상 `@DateTimeFormat(pattern = "yyyy-MM-dd")`을 사용합니다.
- 인증 및 권한 처리는 Spring Security와 연동하며, `User` 엔티티는 `UserDetails`를 구현합니다.
- 비밀번호는 반드시 BCrypt로 해시 처리하여 저장합니다.

## 컨트롤러 패턴

- MVC 컨트롤러는 `@Controller`를 사용하며, 뷰 이름을 반환합니다.
- REST API 컨트롤러는 `@RestController`를 사용하며, JSON을 반환합니다.
- 인증된 사용자 정보는 `Authentication` 파라미터를 통해 얻고, `userService.findByUsername(authentication.getName())`으로 조회합니다.
- URL 패턴은 `/user/todos`, `/users/{id}` 등 역할별로 명확하게 구분합니다.

## 예외 처리

- 데이터 조회 시 존재하지 않는 경우 `RuntimeException` 또는 `UsernameNotFoundException`을 명확히 throw합니다.
- 서비스/레포지토리 계층에서 예외 발생 시, 적절한 메시지를 포함하여 예외를 던집니다.

## 기타

- Thymeleaf 템플릿과 연동되는 경우, Model 객체에 필요한 데이터를 명확히 추가합니다.
- Gradle 빌드 시 컴파일러 옵션에 `-parameters`를 추가하여 리플렉션 지원을 강화합니다.
- 불필요한 import는 제거하고, 필요한 경우만 사용합니다.
