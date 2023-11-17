# study-springboot
스프링부트 저장소 다시 만들기

## chap92. SpringBoot ToyProject
배운 것 모아 토이프로젝트2

### A. 프로젝트 생성
1. 명령 팔레트 시작
2. Spring Initializr: Create a Gradle Project...
3. Spring Boot version : 3.0.12
4. Project language : Java
5. Group Id : com.hugo83 (개인마다 자신의 것으로)
6. Artifact Id : grupal (스페인어 공동)
7. Packaging type : Jar (무난)
8. Java version : 17
9. Dependencties : Spring Boot DevTools, Lombok, Spring Web, Thymeleaf, Spring Data JPA, MariaDB Driver

### B. 프로젝트 설정
1. DB 생성 및 설정 by HeidiSQL
	- grupal 로 DB 생성
	- grupaluser / grupalp@ssw0rd 로 사용자 생성 및 DB 사용지정

2. build.gradle 편집
	```tex
	dependencies {
		// Spring Boot Basic
		implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
		implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
		implementation 'org.springframework.boot:spring-boot-starter-web'
		developmentOnly 'org.springframework.boot:spring-boot-devtools'
		testImplementation 'org.springframework.boot:spring-boot-starter-test'
		// Lombok 기본/ 테스트
		compileOnly 'org.projectlombok:lombok'
		annotationProcessor 'org.projectlombok:lombok'
		testCompileOnly 'org.projectlombok:lombok'
		testAnnotationProcessor 'org.projectlombok:lombok'
		// MariaDB
		runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
	}
	```
3. application.properties 편집
	```tex
	## 한글 인코딩
	server.servlet.encoding.charset=UTF-8
	server.servlet.encoding.enabled=true
	server.servlet.encoding.force=true
	## 콘솔로그 색상
	spring.output.ansi.enabled=always
	## DB 설정
	spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
	spring.datasource.url=jdbc:mariadb://localhost:3326/grupal
	spring.datasource.username=grupaluser
	spring.datasource.password=grupalp@ssw0rd
	## Spring Data JPA 설정
	## create-drop | update
	spring.jpa.hibernate.ddl-auto=update  
	spring.jpa.properties.hibernate.format_sql=true
	spring.jpa.show-sql=true
	```

