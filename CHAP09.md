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