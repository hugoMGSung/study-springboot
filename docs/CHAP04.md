# study-springboot
스프링부트 저장소 다시 만들기

## chap04. Request/Response
요청/응답 테스트

### A. 개념
1. 데이터 분류
	- 정적데이터 : HTML, CSS, javascript 파일 등 전달 후 클라이언트(웹브라우저)에서 동작
	- 동적데이터 : 서버에 요청에 따라 다르게 데이터를 구성 후 클라이언트로 전달. Server Side Programming --> Backend의 핵심
2. Java 웹 개발기술 역사
	- Servlet, EJB에서 JSP, Spring, Spring Boot 까지...

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0009.png" width="600">
3. GET/POST
	- GET : URL 창에 주소와 같이 보내는 텍스트로 데이터 전달. URL뒤의 ?, &, = 가 중요함(쿼리스트링 / 파라미터)
	- POST : 입력화면(Form)에 내용 입력 후 전송버튼(Submit) 클릭으로 데이터 전달

### B. GET 테스트
1. Servlet 우선
2. IntelliJ에서 chap03 폴더 그대로 사용
3. HelloServlet 클래스 생성
	```java
	package com.hugo83;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import java.io.IOException;

	@WebServlet(name = "helloServlet", value = "/hello")
	public class HelloServlet extends HttpServlet {
		public void init() { System.out.println("HelloServlet initialized"); }

		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("doGet() 실행");
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			System.out.println("doPost() 실행");
		}

		public void destroy() {
			System.out.println("destroy() 실행");
		}
	}
	```

4. 한글 깨짐 해결
	1. IntelliJ 파일 > 설정 > 에디터 > 파일 인코딩 UTF-8로 변경
	2. 실행 > 구성 편집 > VM 옵션 -Dfile.encoding=UTF-8 입력 후 적용

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0023.png" width="500">
5. JSP로 GET/POST
6. webapp/calc 폴더 생성
7. input.jsp 및 result.jsp 생성
	```html
	<%-- input.jsp --%>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
		<title>Plus</title>
	</head>
	<body>
		<form method="post" action="result.jsp">
			<input type="number" name="num1">
			<input type="number" name="num2">
			<button type="submit">RESULT</button>
		</form>
	</body>
	</html>

	<%-- result.jsp --%>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
		<title>Result</title>
	</head>
	<body>
	<h1>NUM1 ${param.num1}</h1>
	<h1>NUM2 ${param.num2}</h1>
	<h1>RESULT ${Integer.parseInt(param.num1) + Integer.parseInt(param.num2)}</h1>
	</body>
	</html>
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0010.png" width="600">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0011.png" width="600">

[Next](./CHAP05.md)


