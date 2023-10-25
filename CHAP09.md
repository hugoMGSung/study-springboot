# study-springboot
스프링부트 저장소 VSCode로

## chap09. Spring Web MVC
스프링부트 = 스프링 + 추가기능

1. 서버가 내장되어 별도 WAS 설치 필요없음
2. Starter 기능으로 라이브러리 의존성 관리 편리
3. 스프링에 자주 사용하던 설정을 어노테이션으로 자동등록
4. 내장된 서버가 있어 jar로 배포 단독실행 가능

### A. 프로젝트 생성
1. 명령 팔레트(Ctrl + Shift + P)
2. Spring Initializr: Create a Gradle Project...
3. Spring Boot version: 3.1.4/3.1.5 선택
4. Project language: Java 선택
5. Group Id: com.hugo83 입력 엔터
6. Artifact Id: chap09 입력 엔터
7. Packaging type: War 선택
8. Java version: 17 선택
9. Dependencies 선택없이 엔터
10. study-springboot 폴더에서 Generate Into this folder를 클릭
11. 새로운 VSCode 창을 열지, Workspace를 추가할지 묻는 여부에 Open으로 새창 오픈
	(이렇게 해야 Gradle, SpringBoot Dashboard 제대로 동작함)
12. Whitelabel Error Page 비활성화
	1. application.properties 에 아래 코드 작성
		```tex
		spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
		```
	2. 404 페이지로 변경 확인


### B. JSP 설정
1. build.gradle에 라이브러리 추가
	```tex
	// 버전 적지말것!
	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
	implementation 'javax.servlet:jstl'
	```

2. application.properties 속성 추가
	```tex
	server.error.whitelabel.enabled=false

	spring.mvc.view.prefix=/WEB-INF/views/
	spring.mvc.view.suffix=.jsp

	#JSP수정시 서버 재시작없이 바로 적용될 수 있게 설정
	server.servlet.jsp.init-parameters.development=true
	```

3. src/main/webapp/WEB-INF/views 폴더 추가
4. main.jsp 추가, Ctrl + Space로 HTML 코드스니펫 활용
5. MainController 클래스 추가
	```java
	@Controller
	public class MainController {
		@GetMapping("/")

		public String main() {
			System.out.println("home controller start");

			return "main";
		}
	}
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0040.png" width="600">

### C. Spring Web MVC 진행
0. build.gradle Dependency 추가
1. spring 관련 라이브러리 추가
2. 서블릿 및 jUnit 라이브러리 구성
3. Lombok 라이브러리 추가
4. Log4j2 라이브러리 추가
5. src/main/resources/log4j2.xml 추가/작성
6. MainController.java Log4j 기능 추가/테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0041.png" width="600">

7. webapp/WEB-INF/web.xml 생성,작성
8. webapp/WEB-INF/servlet-context.xml 생성,작성
9. webapp/WEB-INF/root-context.xml 생성,작성
10. java/lombok.config 생성,작성
11. MyBatis 라이브러리 추가
12. main/java/.../mapper/TimeMapper.java 생성,작성
13. main/java/.../mapper/TimeMapper2.java 생성,작성 (XML, SQL 분리용)
14. resources/mappers/TimeMapper2.xml
15. root-context.xml에 설정 추가 // jUnit 라이브러리 설정 오류로 테스트 불가
16. main/java/.../controller/SampleController.java 생성,작성
17. 재시작 후 localhost:8080/hello 확인
18. views/hello.jsp 작성,테스트
19. main/java/.../controller/TodoController.java 작성
	```java
	@Controller
	@RequestMapping("/todo")
	@Log4j2
	@RequiredArgsConstructor
	public class TodoController {
		@RequestMapping("/list")
		public void list() {
			log.info("todo list.......");
		}

		@GetMapping(value = "/register")
		// @RequestMapping(value = "/register", method = RequestMethod.GET)
		public void register() {
			log.info("GET todo register.......");
		}

		@PostMapping(value = "/register")
		public void registerPost() {
			log.info("POST todo 등록!.........")
		}
	}
	```

### D. Spring Boot 콘솔로그 Coloring
1. application.properties 옵션 추가(프로젝트 마다 설정)
	```tex
	spring.output.ansi.enabled=always
	```
2. ~~VSCode 자체 설정. 설정(Ctrl + ,) > workbench color customizations 검색, settings.json에서 편집 클릭~~
	```tex
	// 이 방법 효과없음
	"workbench.colorCustomizations": {
        "terminal.background":"#1D2021",
        "terminal.foreground":"#ffffff",
        "terminalCursor.background":"#A89984",
        "terminalCursor.foreground":"#A89984",
        "terminal.ansiBlack":"#1D2021",
        "terminal.ansiBlue":"#0D6678",
        "terminal.ansiBrightBlack":"#665C54",
        "terminal.ansiBrightBlue":"#0D6678",
        "terminal.ansiBrightCyan":"#8BA59B",
        "terminal.ansiBrightGreen":"#95C085",
        "terminal.ansiBrightMagenta":"#8F4673",
        "terminal.ansiBrightRed":"#FB543F",
        "terminal.ansiBrightWhite":"#FDF4C1",
        "terminal.ansiBrightYellow":"#FAC03B",
        "terminal.ansiCyan":"#8BA59B",
        "terminal.ansiGreen":"#95C085",
        "terminal.ansiMagenta":"#8F4673",
        "terminal.ansiRed":"#FB543F",
        "terminal.ansiWhite":"#A89984",
        "terminal.ansiYellow":"#FAC03B",
      }
	```

### E. Spring Web MVC 계속 
1. Formatter 파라미터 처리
2. 날짜 등을 GET Param으로 넘기면 오류
3. SampleController에 /ex3 테스트
4. (옵션) java/main/.../controller/formatter/LocalDateFormatter.java 작성
5. CheckboxFormatter.java 작성
6. 객체 자료형 파라미터용 java/main/.../dto/TodoDTO.java 작성
7. TodoController 클래스 registerPost메서드의 파라미터로 TodoDTO 적용
8. webapp/WEB-INF/views/todo/register.jsp 작성, 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0042.png" width="600">

9. Model / SampleController /ex4 작성
10. views/ex4.jsp 작성
11. java.lang.ClassNotFoundException: javax.servlet.jsp.tagext.TagLibraryValidator 에러 발생
12. 이는 SpringBoot 3 이상에서 JSTL 사용시 발생하는 에러. build.gradle의 jstl을 1.2에서 아래로 수정
	```tex
	implementation 'org.glassfish.web:jakarta.servlet.jsp.jstl:2.0.0'	 // 업데이트할 것
	```

13. /ex4_1 ~ /ex6까지 작성
14. view/ex6.jsp 작성 후 테스트, http://localhost:8080/ex5 이후 F5 재조회

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0043.png" width="600">

### F. 예외처리
1. web.xml에 error 설정
	```xml
	<error-page>
		<error-code>500</error-code>
		<location>/error/500.jsp</location>
	</error-page>
	
	<error-page>
		<error-code>404</error-code>
		<location>/error/404.jsp</location>
	</error-page>
	```
2. views/404.jsp, 500.jsp 작성
	```html
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ page trimDirectiveWhitespaces="true" %>
	<!DOCTYPE html>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<title></title>
	</head>
	<body>
	<h1>Error Page by web.xml : 404</h1>
	</body>
	</html>
	```

3. http://localhost:8080/aaa/bbb 로 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0044.png" width="600">

