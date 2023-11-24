# study-springboot
스프링부트 저장소 다시 만들기

## chap10. Spring Boot
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

3. 라이브러리 설정
	- build.gradle에 Lombok 테스트용 라이브러리 추가 작성
	- Spring Data JPA 설정 application.properties 
	- Log4j2 라이브러리(기본 추가) 사용설정 application.properties 작성


### D. SpringBoot 웹개발
1. controller 패키지 생성
2. SampleController 클래스 /hello URL 메서드 작성
3. reources/templates/hello.html 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0071.png" width="600">

4. conroller/SampleJSONController 클래스 /helloArr JSON 메서드 작성
5. application/json 데이터 전달 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0072.png" width="600">


### E. Thymeleaf
1. SampleController와 URL 관련 페이지 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0073.png" width="600">

2. 500에러에 대한 오류 수정
	```html
	<h4>[[$(list)]]</h4>
	위의 오류에서 아래로 수정 ...
	<h4>[[${list}]]</h4>
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0074.png" width="600">

3. 레이아웃 기능으로 Thymelaef layout 라이브러리를 build.gradle 에 추가
4. 여기까지 내용 학습

### F. Spring Data JPA
DB에서 테이블을 만들 필요없음

1. domain/BaseEntity.java - 테이블 기본 컬럼 regDate, modeDate 작성
2. domain/Board.java 작성
3. Chap10Application.java에 @EnableJpaAuditing 추가

	엔티티가 DB에 추가되거나 변경될때 자동으로 시간 값 지정하는 리스너

4. repository/BoardRepository.java 인터페이스 작성
5. test/.../repository/BoardRepositoryTests.java 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0076.png" width="500">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0075.png" width="550">

6. 디버그 콘솔에서 Launch Java Tests 콤보박스 선택하며 결과 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0077.png" width="600">

### G. Querydsl로 동적쿼리 처리
단순 JPA는 처리내용이 단순 JPQL은 정적으로 고정되어 불편 그래서 Querydsl이 편리함
단, Querydsl을 사용하려면 버전에 엄청나게 예민해짐(조금만 버전이 달라로 오류!). 오류가 많이 나니 주의할 것!!!

- https://myvelop.tistory.com/213 확인할 것!

1. build.gradle 설정 완전 변경(현재 ~~**chap12**~~로 테스트 했으므로 chap12는 더이상 사용하지 않음!!!!)
	```tex
	plugins {
		id 'java'
		id 'org.springframework.boot' version '3.1.5'
		id 'io.spring.dependency-management' version '1.1.3'
		id 'war'
	}

	group = 'com.hugo83'
	version = '0.0.1-SNAPSHOT'

	java {
		sourceCompatibility = '17'
	}

	configurations {
		compileOnly {
			extendsFrom annotationProcessor
		}
	}

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
		implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
		implementation 'org.springframework.boot:spring-boot-starter-web'
		developmentOnly 'org.springframework.boot:spring-boot-devtools'
		testImplementation 'org.springframework.boot:spring-boot-starter-test'

		runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
		// Lombok 기본
		compileOnly 'org.projectlombok:lombok'
		annotationProcessor 'org.projectlombok:lombok'
		// Lombok 테스트
		testCompileOnly 'org.projectlombok:lombok'
		testAnnotationProcessor 'org.projectlombok:lombok'
		// Thymeleaf 레이아웃
		implementation 'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.1.0'
		// Querydsl 라이브러리
		implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
		annotationProcessor "com.querydsl:querydsl-apt:${dependencyManagement.importedProperties['querydsl.version']}:jakarta"
		annotationProcessor "jakarta.annotation:jakarta.annotation-api"
		annotationProcessor "jakarta.persistence:jakarta.persistence-api"
	}

	tasks.named('test') {
		useJUnitPlatform()
	}

	/**
	* QueryDSL Build Options
	*/
	def querydslDir = "src/main/generated"

	sourceSets {
		main.java.srcDirs += [ querydslDir ]
	}

	tasks.withType(JavaCompile) {
		options.getGeneratedSourceOutputDirectory().set(file(querydslDir))
	}

	clean.doLast {
		file(querydslDir).deleteDir()
	}
	```

2. Gradle / Tasks 의 build > clean 후 build > build 성공하면, other > compileJava 실행
3. 결과는 탐색기에 아래와 같이 생성확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0078.png" width="400">


### H. Repository와 Querydsl 연동
1. search/BoardSearch 에 Querydsl 인터페이스 선언
2. search/BoardSearchImpl.java 로 BoardSearch를 구현해서 작성
3. BoardSearchImpl 의 search1() 메서드에 Q도메인 객체로 작성
4. BoardRepository 선언부에 BoardSearch 인터페이스 추가 지정
	```java
	import com.hugo83.chap10.repository.search.BoardSearch; // 반드시 추가!

	// , BoardSearch 반드시 추가
	public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
		@Query(value = "select now()", nativeQuery = true)
		String getTime();
	}
	```

5. test/.../repository/BoardRepositoryTests 아래에 testSearch1() 작성 후 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0079.png" width="600">

6. search1() 중간에 페이징 처리 추가

	위의 쿼리에 order by와 limit 가 추가됨

7. 검색 조건 추가를 위해 BooleanBuilder 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0080.png" width="600">

8. SearchAll() 작성


### I. 게시물 관리 완성
1. build.gradle에 modelmapper(DTO와 엔티티간 변환처리) 및 validation 라이브러리 추가
	```tex
	// ModelMapper 라이브러리
	implementation 'org.modelmapper:modelmapper:3.1.0'
	// Validation 처리 라이브러리
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	```
2. config/RootConfig.java 클래스 추가
3. dto/BoardDTO 클래스 작성
4. service/BoardService 인터페이스와 BoardServiceImpl 클래스 생성

5. 등록작업용 register() 작성
6. service/BoardServiceTests.java 에 testRegister() 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0081.png" width="600">

7. 조회, 수정, 삭제 메서드 추가 작성
8. dto/PageRequestDTO.java, PageResponseDTO.java 추가 작성
9. BoardService, BoardServiceImple에 list() 작성

10. controller/BoardController.java 작성으로 컨트롤러와 화면처리 시작
11. build.gradle에 화면 구성을 위한 라이브러리 추가

12. https://startbootstrap.com/template/simple-sidebar 다운받은 압축내용을 resources/static 아래 붙여넣기

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0082.png" width="600">

13. templates/layout/basic.html 생성 후 index.html 복사 그대로 붙여넣기
14. basic.html에 Thymeleaf용 내용 추가
15. templates/board/list.html 생성 및 작성 후 실행확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0084.png" width="600">

16. list.html 검색내용 추가
17. list.html 내 js 이벤트 처리
18. BoardController에 /register GET/POST 메서드 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0085.png" width="600">

19. register.html 작성 및 list.html 수정
20. BoardController /read 작성
21. list.html에서 read 링크 처리
22. BoardController /read에 /modify 추가 작성
23. modify.html 작성
24. 삭제 처리


[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP11.md)