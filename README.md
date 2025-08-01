# GitHub Copilot Workshop
안녕하세요. GitHub Copilot Workshop 리포지토리 입니다. 
이 Repository는 여러개의 다른 실습 과제들을 담고 있으며, GitHub Copilot의 기본적인 코드를 제안받는 사용법 부터 추가적인 다른 워크플로우에 이용할 수 있는 방법까지 핸즈온 경험을 기반으로 한 워크샾 내용을 담고 있습니다. 
각 프로젝트는 GitHub Copilot의 서로 다른 사용례를 담고 있으며, 개별적인 실습 과제로서 마무리 됩니다.

## 실습 환경
- IntelliJ IDEA를 활용합니다.
  * Task 6에서 실습하는 Java 버전 upgrade 실습은 VS Code에서만 지원되어, VS Code를 활용합니다.
- GitHub Copilot 플러그인을 설치하고, GitHub Copilot Business 라이센스가 있는 계정으로 로그인하여 사용할 수 있는 상태여야 합니다.
- GitHub Copilot 플러그인은 **최신 버전으로 업데이트 되어 있어야 합니다.**

  ### 언어 및 빌드
  - Java 11, Gradle
  - Spring Boot 2.7.18
  - H2 in-memory database
  - Thymeleaf

## 설명: GitHub Copilot 미리보기
 - GitHub Copilot의 기본적인 내용에 대해 자료를 통해 설명 드립니다. 
  - [GitHub Copilot 사용 베스트 프랙티스](https://docs.github.com/ko/enterprise-cloud@latest/copilot/using-github-copilot/best-practices-for-using-github-copilot)
  - [GitHub Copilot in VS Code](https://code.visualstudio.com/docs/copilot/overview)
  - [초보자를 위한 GitHub Copilot의 핵심기능소개](https://github.blog/ai-and-ml/github-copilot/github-for-beginners-essential-features-of-github-copilot/)

## [Task 1](/Task01/README.md): 간단한 함수 및 테스트 코드 제안 받기 (코드완성)
 - GitHub Copilot를 활용하여 간단한 함수와 테스트 코드를 제안받는 실습입니다. 이를 통해 기본적인 Copilot의 기능을 활용하는 방법을 익힙니다. 
 - GitHub Copilot Log를 확인하여, 오픈소스와 매치되는 코드인 경우 레퍼런스 정보를 확인합니다. 
 - IntelliJ IDEA의 Copilot 메뉴에 관한 기본적인 설정들을 변경해 봅니다. 

## [Task 2](/Task02/README.md): 가위, 바위, 보 게임 만들기 (Copilot Chat사용)
 - GitHub Copilot과 함께 가위, 바위, 보 게임을 만들어 봅니다.
 - Copilot을 통해 테스트 코드를 작성해 봅니다.
 - Copilot chat의 각종 메뉴들을 사용해 봅니다.

## [Task 3](/Task03/README.md): Spring Boot 기반 Todo App 개발 (Part 1)
- 프로젝트를 구성하기 위해, Copilot에게 Custom instruction을 제공하는 방법을 실습합니다.
- Copilot으로 Commit message를 자동 생성해 봅니다. 이 때, 원하는 형태의 Commit message를 생성하기 위해, Custom instruction을 활용합니다.
- (선택사항) GitHub MCP Server를 설정하는 방법을 익힙니다. (이후 Task 4에서 실습)

## [Task 4](/Task04/README.md): Spring Boot 기반 Todo App 개발 (Part 2)
- 이전 Task03(Part 1)에서 생성된 커스텀 instructions를 활용하여 Agent모드에서 Spring Boot 기반 Todo App을 실제로 빌드합니다. 
- GitHub Copilot의 프리미엄 리퀘스트에 대해 학습합니다.
- Agent 모드에서 개발과정의 내용을 커밋하고 , 커밋 메시지를 작성하도록 프롬프트로 요청합니다. 
- TODO앱에 '즐겨찾'를 추가하는 기능도 Agent 모드에서 구현합니다

## [Task 5](/Task05/README.md): 프롬프트와 컨텍스트 사용 
- 효율적인 프롬프트 작성과 컨텍스트 활용 방법을 익힙니다. 
- Copilot Chat에 추가할 수 있는 다양한 컨텍스트들에 대해 확인합니다. 

## [Task 6](/Task06/README.md): Copilot을 통해 Java version 업그레이드, 코드 리팩토링, 보안 문제 확인
- GitHub Copilot을 활용하여 Java version 업그레이드를 진행합니다. (VS Code활용)
- GitHub Copilot을 활용해 코드 리팩토링과 보안 문제를 확인합니다.

## [Task 7](/Task07/README.md): MCP (Model Context Protocol) 활용 실습
 - IntelliJ IDEA에서 Model Context Protocol 서버를 설정하고, 활용해 봅니다.
   

