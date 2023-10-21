# study-springboot
스프링부트 저장소 다시 만들기

[자바 웹 개발 워크북 학습](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=298759546) /구멍가게 코딩단 /프리렉 /2022-08-04

## chap00. 기본 JSP 개발환경 설정
- 개발환경 만들기
	- JDK 17
	- Gradle 8.1.1
	- Visual Studio Code
	- Tomcat Server 9.0.41

### A. JDK 17 설치
1. https://www.oracle.com/kr/java/technologies/downloads/ 
2. x64 MSI Installer 다운로드 및 설치
3. 시스템 속성에 환경변수로 JAVA_HOME 등록, Path에 포함
4. java -version 으로 확인

### B. Gradle 8.1.1 설치
1. https://gradle.org/releases/ 
2. 8.1.1 다운로드 및 압축해제
3. 시스템 속성에 환경변수로 GRADLE_HOME 등록, Path에 포함
4. gradle -version 으로 확인

### C. VSCode 및 Plugin 설치
1. https://code.visualstudio.com/
2. Windows x64, User Installer Stable 다운로드 후 설치
3. Korean Language Pack for Visual Studio Code 설치 후 재시작
4. Extension Pack for Java 설치
5. Gradle for Java 설치
6. Spring Boot Extension Pack 설치
7. Community Server Connectors 설치

### D. Tomcat Server 9.0.41 설치
1. 탐색기 SERVERS 클릭
2. Community Server Connector RMouse > Create New Server...
3. Download server? Yes
4. Please choose a Server to download... > Apache Tomcat 9.0.41 클릭
5. License Yes
6. 설치 후 Community Server Connector 아래 apache-tomcat-9.0.41 확인
7. RMouse > Start Server
8. 실행 로그 텍스트 깨짐 확인 RMouse > Edit Server 로 톰캣 위치 확인
9. 톰캣 base dir/conf/logging.properties 오픈 utf-8 -> euc-kr로 변경 저장
10. Start Server 실행
11. 톰캣 base dir/conf/tomcat-users.xml 에 관리자 계정 추가

### E. Gradle로 프로젝트 생성
1. 챔터 chap00 생성
2. 콘솔 오픈, chap00 이동 후 gradle init 실행
	1. Select type of project to generate: 2. application
	2. Select implementation language: 3. Java
	3. Generate muliple subprojects for application? no
	4. Select build script DSL: 1. Groovy
	5. Select test framework: 4. JUnit Jupiter
	6. Project name: chap00 
	7. Source package: chap00
	8. Enter target version of Java: 17
	9. Generate build using new APIs.... no
3. chap00으로 새 VSCode 오픈
	1. Gradle 탭 chap00 > Tasks > application > run 실행 확인
4. app/src/main/webapp 폴더 생성
5. build.gradle 수정
	```text
	dependencies {
		testImplementation 'org.junit.jupiter:junit-jupiter:5.9.1'
		testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.9.1'

		compileOnly 'javax.servlet:javax.servlet-api:4.0.1'
		compileOnly 'javax.servlet.jsp:javax.servlet.jsp-api:2.3.3'

		implementation 'org.glassfish:javax.json:1.1.4'
		implementation 'org.apache.logging.log4j:log4j-api:2.20.0'
		implementation 'org.apache.logging.log4j:log4j-core:2.20.0'

		annotationProcessor 'org.apache.logging.log4j:log4j-api:2.20.0'
		annotationProcessor 'org.apache.logging.log4j:log4j-core:2.20.0'
	}
	```
6. Tomcat 내 Server Actions... > Edit Configuration File... > conf/server.xml 오픈
	```xml
	<Context path="/app" docBase="D:\...\chap00\app\src\main\webapp"></Context>
	```
7. webapp/index.jsp 추가
	```html
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<html lang="ko">
		<head>
			<title>VS CODE에서 JSP 동작시키기</title>
		</head>
		<body>
			<h1>첫 페이지입니다.</h1>
		</body>
	</html>
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0019.png" width="600">

8. index.jsp 변경 후 테스트
	```html
	...
	<body>
	<h1><%= "Hello World!" %></h1>
	<h1><%= "Hello World!" %></h1>
	<h1><%= "Hello World!" %></h1>

	<br/>
	<a href="hello-servlet">Hello Servlet</a>
	...
	```

### F. Servlet 구현
오류가 나면서 제대로 안됨. 방법을 틈

## chap02. Spring으로 Servlet 프로젝트 구성

### A. 스프링 프로젝트 템플릿 만들기
1. https://start.spring.io/ 에 접근

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0021.png" width="600">
2. Spring Initializr
	1. Project: Gradle - Groovy
	2. Language: Java
	3. Spring Boot: 3.1.4
	4. Project Metadata
		- Group: com.hugo83
		- Artifact: chap02
		- Packaging: War
		- Java: 17
	5. Dependencies
		- Spring Web
	6. Generate 버튼 클릭으로 chap02.zip 다운로드 
3. chap02 폴더에 압축해제
4. VSCode에서 오픈
5. 터미널에서 .\gradlew 실행
6. 빌드 .\gradlew war 실행
7. 실행해보기 .\gradlew bootRun 
8. 실행결과는 동일

### B. 스프링제거 후 Servlet으로 변경
1. application.properties 수정
	```tex
	server.error.whitelabel.enabled=false
	server.error.path=/error
	spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
	```
	ERROR!! Spring Servlet과 HttpServlet 충돌

## chap01. Spring Boot 개발환경 설정
- 개발환경 만들기
	- JDK 17
	- Gradle 8.1.1
	- Visual Studio Code

### A. JDK 17 설치
1. 상동, 생략

### B. Gradle 8.1.1 설치
1. 상동, 생략

### C. VSCode 및 Plugin 설치
1. 상동, 생략

### D. Spring Boot 프로젝트 생성
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

### E. .gitignore 를 수정
- */.gitignore 를 root ,gitignore에 추가할 것
- .gradle, .vscode, build 등이 깃허브에 올라가지 않는지 확인 요

### F. chap01 Spring Boot 프로젝트 VSCode
1. Chap01Application.java 에 아래의 내용 코딩
	```java
	import org.springframework.web.bind.annotation.*;

	@RestController
	class Helloworld {
		@GetMapping("/")
		public String hello() {
			return "Hello World!";
		}
	}
	```

2. 터미널 (Ctrl + `) 오픈
	1. gradlew tasks 로 확인해 볼 것
	2. 명령어 실행
	```shell
	> .\gradlew bootRun
	```

	3. 터미널 로그에 Tomcat started on port(s): 8080 (http) 를 확인 후 브라우저 오픈
	
	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0018.png" width="600">

	4. 종료시는 Ctrl + C 입력 후 일괄 작업을 끝내시겠습니까 (Y/N)? Y 로 종료
3. Spring Boot Dashboard
	1. App의 chap01 오른쪽 플레이 버튼 클릭
	2. 종료는 새로 생기는 탭에서 종료 버튼 클릭

4. 스프링부트 로그 칼라링
	1. src/resources/application.properties 오픈 
	```tex
	spring.output.ansi.enabled=always
	```

[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP03.md)
