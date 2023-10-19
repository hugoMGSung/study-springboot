# study-springboot
스프링부트 저장소 다시 만들기

## chap01. Spring Boot 개발환경 설정
- 개발환경 만들기
	- JDK 21
	- Gradle 8.1.1
	- Visual Studio Code

### JDK 21 설치
1. https://www.oracle.com/kr/java/technologies/downloads/ 
2. x64 MSI Installer 다운로드 및 설치
3. 시스템 속성에 환경변수로 JAVA_HOME 등록, Path에 포함
4. java -version 으로 확인

### Gradle 8.1.1 설치
1. https://gradle.org/releases/ 
2. 8.1.1 다운로드 및 압축해제
3. 시스템 속성에 환경변수로 GRADLE_HOME 등록, Path에 포함
4. gradle -version 으로 확인

### VSCode 및 Plugin 설치
1. https://code.visualstudio.com/
2. Windows x64, User Installer Stable 다운로드 후 설치
3. Korean Language Pack for Visual Studio Code 설치 후 재시작
4. Extension Pack for Java 설치
5. Gradle for Java 설치
6. Spring Boot Extension Pack 설치

### Spring Boot 프로젝트 생성
1. 명령 팔레트... (Ctrl+Shift+P) 시작
2. Spring Initializr: Create a Gradle Project... 클릭
3. Spring Boot version, 3.1.4 선택
4. Project language, Java 선택
5. Group Id, com.example을 변경 (com.hugo83)
6. Artifact Id, demo를 변경 (chap01)
7. Packaging type, War 선택
8. Java version, 17로 선택 (21 무방)
9. Dependencies 0 
10. 프로젝트 깃 위치 그대로 선택(폴더 생성하면 폴더아래 폴더 하나더 생성됨)
11. Support for Java에서 Open 버튼 클릭해서 새 VSCode 오픈할 것!
	- 만약 새로 열지 않으면 Gradle, Spring Boot Dashboard와 연계가 제대로 안됨

### .gitignore 를 수정