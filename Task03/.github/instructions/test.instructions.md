---
applyTo: '**/*Test.java*'
---

# 테스트 코드 작성 및 리뷰 지침

## 테스트 프레임워크 및 환경

- 모든 테스트는 JUnit 5(`org.junit.jupiter`) 기반으로 작성합니다.
- Spring Boot Test(`@SpringBootTest`)를 사용하여 통합 테스트를 지원합니다.
- 단위 테스트에서는 Mockito(`@Mock`, `@InjectMocks`, `MockitoAnnotations.openMocks`)를 활용하여 의존성 객체를 mocking합니다.
- 테스트 클래스는 `src/test/java/com/keon/todoapp/` 하위에 위치합니다.

## 주요 테스트 패턴

- 서비스 레이어 테스트에서는 Repository와 PasswordEncoder 등 외부 의존성을 모두 mock 처리합니다.
- 예외 상황(존재하지 않는 User 등)에 대한 테스트를 반드시 포함합니다.
- 테스트 메서드명은 동작과 기대 결과를 명확히 표현합니다. 예시: `registerUser_ShouldEncodePasswordAndSaveUser`
- `assertThrows`, `assertEquals`, `assertNotNull` 등 JUnit assertion을 적극적으로 사용합니다.

## 테스트 코드 예시

- UserService의 회원가입, 조회, 예외 처리 등 핵심 비즈니스 로직을 단위 테스트로 검증합니다.
- Spring Boot Application의 context load 여부를 `TodoappApplicationTests`에서 확인합니다.

## 테스트 실행 및 결과

- Gradle 명령어로 테스트를 실행합니다: