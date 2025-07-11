# study-springboot
스프링부트 저장소 다시 만들기

## chap05. Servlet Web MVC

<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0014.png" width="700">

### A. 새 프로젝트 만들기
1. IntelliJ 새 프로젝트 클릭
2. 새 프로젝트 창
	1. 이름: chap05
	2. 위치: D:\...\study-springboot
	3. 언어: Java
	4. 시스템 빌드: Gradle
	5. JDK: 17
	6. Gradle DSL: Groovy
	7. 샘플코드 추가
	8. 고급설정 > 그룹ID: com.hugo83
	9. 고급설정 > 아티팩트ID: chap05
	10. 생성 버튼 클릭
3. build.gradle 변경
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
		junitVersion = '5.9.1'
	}

	sourceCompatibility = '1.8'
	targetCompatibility = '1.8'

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


### B. 컨트롤러에서 뷰 호출
1. calc/InputController.java 추가
	```java
	@WebServlet(name = "inputController", urlPatterns = "/input") // urlPatterns 확인할 것
	public class InputController extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			System.out.println("InputController...doGet...");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/calc/input.jsp");
			dispatcher.forward(request, response);
		}
	}
	```
2. webapp/WEB-INF/calc/input.jsp 추가, 내용은 동일
3. Tomcat 구성
	1. 실행 > 구성편집
	2. 이름 지정
	3. 애플리케이션 서버: Tomcat 9.0.82 선택
	4. 브라우저 열기: 시작 후 Chrome
	5. VM 옵션: -Dfile.encoding=UTF-8
	6. 배포탭, 서버 시작 시 배포: Gradle : com.hugo83 : chap05-1.0-SNAPSHOT.war (exploded) 선택
	7. 애플리케이션 컨텍스트: / 로 변경
	8. 확인 버튼

### C. 포스트 방식으로 통한 처리
1. CalcController 클래스 생성
	```java
	@WebServlet(name = "calcController", urlPatterns = "/calc/makeResult")
	public class CalcController extends HttpServlet {
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String num1 = request.getParameter("num1");
			String num2 = request.getParameter("num2");

			System.out.printf("num1 : %s", num1);
			System.out.printf(" num2 : %s", num2);

			response.sendRedirect("/index");
		}
	}
	```
2. input.jsp action 추가
	```html
	<form method="post" action="/calc/makeResult">
	```

3. 결과

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0024.png" width="600">

	GET, POST 후 Redirect를 처리하는 방식을 POST-Redirect-GET(PRG) 라고 함

[Next](./CHAP06.md)