# study-springboot
스프링부트 저장소 다시 만들기

## chap09. Spring Web MVC
스프링부트 = 스프링 + 추가기능

1. 서버가 내장되어 별도 WAS 설치 필요없음
2. Starter 기능으로 라이브러리 의존성 관리 편리
3. 스프링에 자주 사용하던 설정을 어노테이션으로 자동등록
4. 내장된 서버가 있어 jar로 배포 단독실행 가능
5. JSP 보다는 Thymeleaf, MyBatis 보다는 JPA
6. 계속 발전되고 있는 프레임워크 도구

### A. 스프링부트 프로젝트 생성방식
1. Spring Initializr 웹사이트 사용
2. Spring Initializr 마법사 사용(V)
3. Maven이나 Gradle 직접 사용

### B. VSCode Spring Initializr Wizard
1. 명령 팔레트(Ctrl + Shift + P)
2. Spring Initializr: Create a Gradle Project...
3. Spring boot version: 3.1.5
4. Project language: Java
5. Groupd Id: com.hugo83
6. Artifact Id: chap10
7. Packaging type: War
8. Java version: 17
9. Dependencies
	- Spring Boot DevTools
	- Lombok
	- Spring Web
	- Thymeleaf
	- Spring Data JPA
	- MariaDB Driver
10. springboot 프로젝트 디렉토리에서 Generate into this Folder
11. Open 버튼 클릭

12. Gradle 탭과 Spring Boot Dashboard 체크
13. Gradle 탭, build > build로 새 빌드
14. Gradle 탭, application > bootRun으로 서버 실행 / 

	Spring Boot Dashboard, Apps > chap10 Play로 서버 실행

15. MariaDB, JPD 등 DB 설정하지 않으면 실행되지 않음

16. src/main/resources/application.properties 또는 .yml 설정
	```tex
	## DB 설정
	spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
	spring.datasource.url=jdbc:mariadb://localhost:3326/webdb
	spring.datasource.username=webuser
	spring.datasource.password=webuser
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0068.png" width="600">

### C. 추가 설정 팁
1. 톰캣 재구동없이 실행
	- spring-boot-devtools 
	- 설정(Ctrl + ,) 클릭
	- Hot Code Replace > auto

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0069.png" width="550">

2. 기본 에러페이지(Whitelabel Error Page) 제거, 커스텀 404 추가
	- application.properties
		```tex
		server.error.whitelabel.enabled=false
		server.error.path=/error
		spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
		```
	- resource/templates 하위에 에러 페이지 작성(with Bootstrap)

		404, 500, error.html
	
	- bg1.jpg는 resources/static/img에 저장
	
	- ErrorController 상속받은 CustomError 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0070.png" width="600">

	


