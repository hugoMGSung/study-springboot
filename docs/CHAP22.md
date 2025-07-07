# study-springboot
스프링부트 저장소 다시 만들기(with Oracle)

## chap22. Spring Boot tutorial

### A. 프로젝트 생성
1. Ctrl + Shift + P, Spring Initializr: Create a Gradle Project...
2. Specify Spring Boot version : 3.2.x 이상 선택
3. Specify project language : Java 
4. Input Group Id : com.hugo83 등 자신의 도메인으로 입력
5. Input Artifact Id : sbbo 등 자신의 호스트로 입력
6. Specify packaging type : Jar 선택
7. Specify Java version : 17 선택
8. Choose dependencies : Spring Web 만 선택
9. 폴더 선택 후 Open

<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0322.png" width="700">

### B. Hello화면 작업
1. src/main ... sbbo/controller 폴더 생성
2. HelloController.java 파일 생성

	```java
	@RestController
	public class HelloController {
		@GetMapping("/hello")
		public String getHello() {
			return "Hello, SpringBoot!";
		}
	}
	```
3. 실행결과

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0323.png" width="700">

4. 수정발생시 자동 재빌드 설정
	- application.properties 코드 추가

	```markdown
	# 수정사항 생길시 자동 재빌드를 위한 코드
	spring.devtools.livereload.enabled=true
	spring.devtools.restart.enabled=true
	```

	- build.gradle에 dependency 추가

	```gradle
	implementation 'org.springframework.boot:spring-boot-devtools'
	```

5. Whiteabel Error Page 제거설정(404)
	- application.properties 코드 추가

	```markdown
	## Whiteabel Error Page 제거
	server.error.whitelabel.enabled=false
	server.error.path=/error
	spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
	```

	- 또는 /resources/static/index.html 추가

