# study-springboot
스프링부트 저장소 다시 만들기(with Oracle)

## chap24. Spring Boot tutorial

### A. 프로젝트 생성
1. Ctrl + Shift + P, Spring Initializr: Create a Gradle Project...
2. Specify Spring Boot version : 3.2.x 이상 선택
3. Specify project language : Java 
4. Input Group Id : com.hugo83 등 자신의 도메인으로 입력
5. Input Artifact Id : bboard 등 자신의 호스트로 입력
6. Specify packaging type : Jar 선택
7. Specify Java version : 17 선택
8. Choose dependencies : Spring Boot Data JPA, thymeleaf, Spring Boot Web, lombok, Spring Boot Devtools, Oracle Driver 여섯개 선택

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0324.png" width="800">

9. 폴더 선택 후 Open

### B. 초기설정
1. application.properties 설정

	```properties
	## 기본 설정
	spring.application.name=oboard

	## 포트변경
	server.port=8090

	## 로그 색상
	spring.output.ansi.enabled=always

	## 수정사항 생길시 자동 재빌드를 위한 코드
	spring.devtools.livereload.enabled=true
	spring.devtools.restart.enabled=true

	## Oracle 설정
	spring.datasource.username=sbboard
	spring.datasource.password=1234
	spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
	spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

	## JPA설정
	spring.jpa.show-sql=true
	spring.jpa.properties.hibernate.format_sql=true
	## 아래 버전차이로 오류 발생!??
	# spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect

	# Database management policies
	# update: create once
	# create: create every time of application up
	# create-drop: create at the start of app and drop before end of app
	# create : 기존 테이블 삭제 후 다시 생성
	# create-drop : create와 동일하나 종료 시점에 table 삭제
	# update : 주어진 구조에 따라 엔티티 변경
	# validate : 정상 매핑 되었는지만 확인
	# none: 변화 없음
	# 실제 서비스 배포시에는 create, create-drop등은 사용하지 
	spring.jpa.hibernate.ddl-auto=create-drop

	## thymeleaf 참조 경로
	spring.thymeleaf.prefix=classpath:/templates/
	spring.thymeleaf.suffix=.html

	## thymeleaf에 대한 캐시없앰. cache=false 설정(운영시는 true)
	spring.thymeleaf.cache=false
	## templates 디렉토리에 파일여부 체크, 없으면 에러
	spring.thymeleaf.check-template-location=true
	```

## C. JPA 작업
1. /entity/Board.java 작성
	```java
	@Entity
	public class Board {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long bno;
		
		private String title;

		private String content;

		private String writer;

		@CreatedDate
		@Column(name = "regdate", updatable = false)
		private LocalDateTime regDate;

		@LastModifiedDate
		@Column(name = "moddate")
		private LocalDateTime modDate;
	}
	```

2. /repository/BoardRepository.java 작성
	```java
	@Repository
	public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	}
	```

3. OBoardApplication.java 수정
	```java
	@SpringBootApplication
	public class OboardApplication implements CommandLineRunner {

		@Autowired
		private BoardRepository boardRepository;

		// main 생략

		@Override
		public void run(String... args) throws Exception {
			Board b1 = Board.builder().title("첫번째 게시물입니다.").content("첫번째 게시물입니다. 감사합니다.")
					.writer("휴고성").regDate(LocalDateTime.now()).build();
			Board b2 = Board.builder().title("두번째 게시물입니다.").content("두번째 게시물입니다. 안녕히가세요.")
					.writer("애슐리").regDate(LocalDateTime.now()).build();

			List<Board> boardList = List.of(b1, b2);
			boardRepository.saveAll(boardList);
		}
	}
	```

4. 실행결과

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0333.png" width="800">

5. application.properties 로그레벨 추가
	```properties
	## 로그레벨 설정
	logging.level.org.springframework=info
	logging.level.org.zerock=debug
	```

6. 이하 작업할 때는 
	- application.properties 의 spring.jpa.hibernate.ddl-auto=update 로
	- OBoardApplication.java run()을 주석처리할 것

## D. Thymleaf와 연계
1. /resources/templates/index.html 생성 및 실행
	- 코드 생략 
	- 실행 결과

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0334.png" width="800">

2. /controller/SampleController.java 작성

	```java
	@Controller
	@Log4j2
	public class SampleController {
		@GetMapping("/hello")
		public void hello(Model model) {
			String msg = "Hello, Hugo's SpringBoot~!";
			log.info(msg);

			model.addAttribute("msg", msg);
		}
	}
	```

3. hello.html 작성 및 실행결과
	```html
	<!doctype html>
	<html lang="ko" xmlns:th="http://www.thymeleaf.org">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
			crossorigin="anonymous"></script>
		<title>Hello Title!</title>
	</head>
	<body>
		<h2 th:text="${msg}"></h2>
	</body>
	</html>
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0332.png" width="800">