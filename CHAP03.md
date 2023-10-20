study-springboot
스프링부트 저장소 다시 만들기

## chap03. 기본 웹개발환경 맛보기
이를 위해 임시로 JetBrains IntelliJ IDEA 사용

### A. IntelliJ 설치
PASS

### B. 프로젝트 생성
1. IntelliJ 실행 후 New Project 클릭
	1. Name: chap03
	2. Location: 지정
	3. Language: Java
	4. Build system: Gradle
	5. JDK: 설치한 17.0.9
	6. Gradle DSL: Groovy
	7. Create 클릭
2. be paitent... ^^
3. .idea, .gitignore에 추가
4. build.gradle 수정
	```tex
	plugins {
		id 'java'
		id 'war'
	}

	group = 'com.hugo83'
	version = '1.0-SNAPSHOT'

	repositories {
		mavenCentral()
	}

	ext {
		junitVersion = '5.8.1'
	}

	sourceCompatibility = '1.8'
	targetCompatibility = '1.8'

	tasks.withType(JavaCompile) {
		options.encoding = 'UTF-8'
	}

	dependencies {
		compileOnly('javax.servlet:javax.servlet-api:4.0.1')

		testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
		testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")

		implementation group: 'jstl', name: 'jstl', version: '1.2'
	}

	test {
		useJUnitPlatform()
	}
	```
4. Tomcat 설치
	1. https://tomcat.apache.org/download-90.cgi 9.0.82 다운로드 설치
	2. 기본 설치 
	3. Server Port: -1 -> 8001 or 8002
	4. Admin: admin/admin
	5. JDK: 17 선택
	6. etc...
5. Tomcat 설정
	1. 실행(Run) > 구성편집(Edit Configurations...)
	2. + 클릭 > Tomcat Server > Local 선택
		1. 이름: IntelliJ Tomcat 
		2. 구성(Configure) 클릭
		3. Tomcat 위치 적용
		4. 브라우저 열기 시작 후 Chrome으로 변경
		5. 수정 클릭
		6. Gradle: com.hugo83: chap03-1-SNAPSHOT.war (exploded) 선택
		7. 애플리케이션 컨텍스트: / 로 변경
		8. 확인 버튼 클릭
	3. Tomcat 서비스 실행
	4. 로그 한글깨짐
	5. 전체 검색에서 vm 검색 후 '사용자 지정 VM 옵션 편집...' 선택
	6. 맨아래 다음 입력
		```tex
		-Dfile.encoding=UTF-8
		-Dconsole.encoding=UTF-8
		```
	7. IntelliJ 재시작
6. src/main/webapp 폴더 생성
7. index.jsp 추가
	```html
	<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html lang="ko">
	<head>
		<title>IntelliJ JSP</title>
	</head>
	<body>
	<h1><%= "Hello World!" %></h1>
	</body>
	</html>
	```
8. 모듈 'chap03' 빌드
9. Tomcat 실행, Chrome 결과 확인

### C. Servlet 테스트
1. index.jsp 추가
	```html
	<div>
		<a href="./firstServlet">First Servlet</a>
	</div>
	```
2. src/main/java/ 폴더 아래 com.hugo83 패키지 생성
3. 아래 FirstServlet.java 생성
	```java
	package com.hugo83;

	import java.io.*;
	import javax.servlet.http.*;
	import javax.servlet.annotation.*;

	@WebServlet(name = "firstServlet", value = "/firstServlet")
	public class FirstServlet extends HttpServlet {
		private String message;

		public void init() {
			message = "Hello Servlet!";
		}

		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
			response.setContentType("text/html");

			// Hello
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<h1>" + message + "</h1>");
			out.println("<h1>" + message + "</h1>");
			out.println("<h1>" + message + "</h1>");
			out.println("<h1>" + message + "</h1>");
			out.println("</body></html>");
		}

		public void destroy() {
		}
	}
	```
4. 톰캣 서버 실행
	1. First Servlet 링크 클릭
	2. 결과

		<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0022.png" width="600">

[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP04.md)