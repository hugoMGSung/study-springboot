# study-springboot
스프링부트 저장소 다시 만들기

## chap21. SpringBoot ToyProject
배운 것 모아 토이프로젝트1

### A. VSCode 설정
1. VSCode 확장 선택
2. Lombok Annotations Support for VS Code 설치
3. 필요 추가 예정

### B. 프로젝트 생성
1. 명령 팔레트 시작
2. Spring Initializr: Gradle 선택
3. SPring Boot version: 3.0.12 로 다운선택
4. 나머지, Java > Group Id(본인 것) > tinylibrary > Jar > 17
5. Dependencies : Spring Boot DevTools, Lombok, Spring Web, Thymeleaf, Spring Data JPA, Maria DB Driver 선택
6. 위치 지정 후 Open

### C. 프로젝트 설정
0. DB 생성 및 설정
	- tinylibrary 로 DB생성
	- toyoneuser / toyonepassw0rd 로 사용자 생성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0201.png" width="400">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0202.png" width="600">

1. build.gradle 편집
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
2. Gradle build
3. application.properties 작성
4. https://themewagon.com/themes 에 있는 mazer admin dashboard templates 다운로드

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0205.png" width="600">



### D. 구현 시작
1. 첫번째 컨트롤러 


