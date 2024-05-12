# study-springboot
스프링부트 저장소 다시 만들기(with Oracle)

## chap23. Spring Boot tutorial

### A. 프로젝트 생성
1. Ctrl + Shift + P, Spring Initializr: Create a Gradle Project...
2. Specify Spring Boot version : 3.2.x 이상 선택
3. Specify project language : Java 
4. Input Group Id : com.hugo83 등 자신의 도메인으로 입력
5. Input Artifact Id : std-mng 등 자신의 호스트로 입력
6. Specify packaging type : Jar 선택
7. Specify Java version : 17 선택
8. Choose dependencies : Spring Boot Data JPA, thymeleaf, Spring Boot Web, lombok, Spring Boot Devtools, Oracle Driver 여섯개 선택

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0324.png" width="800">

9. 폴더 선택 후 Open

### B. 초기설정
- 위와 같이 DB 및 JPA 등을 의존성에 추가하면 실행 불가함
- application.properties 
	- 작업 1

	```properties
	## 포트변경
	server.port=8989

	## 로그 색상
	spring.output.ansi.enabled=always

	## 수정사항 생길시 자동 재빌드를 위한 코드
	spring.devtools.livereload.enabled=true
	spring.devtools.restart.enabled=true
	```

	- 작업 2

	```properties
	## Oracle 설정
	spring.datasource.username=atom
	spring.datasource.password=1234
	spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
	spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

	## JPA설정
	spring.jpa.show-sql=true
	spring.jpa.properties.hibernate.format_sql=true
	# 아래 사용하면 오류 발생!
	# spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect

	# Database management policies
	# update: create once
	# create: create every time of application up
	# create-drop: create at the start of app and drop before end of app
	spring.jpa.hibernate.ddl-auto=create-drop
	```

	- 작업 3

	```properties
	## thymeleaf 참조 경로
	spring.thymeleaf.prefix=classpath:/templates/
	spring.thymeleaf.suffix=.html

	## thymeleaf에 대한 캐시없앰. cache=false 설정(운영시는 true)
	spring.thymeleaf.cache=false
	## templates 디렉토리에 파일여부 체크, 없으면 에러
	spring.thymeleaf.check-template-location=true
	```

- /entity/Student.java 작성

	```java
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	@Entity
	@Table(name = "students")
	@Builder
	public class Student {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "pid")
		private int id;
		@Column(name = "name")
		private String name;
		@Column(name = "address")
		private String address;
		@Column(name = "email")
		private String email;
	}
	```

- /repository/StudentRepository.java 작성

	```java
	@Repository
	public interface StudentRepository extends JpaRepository<Student, Integer> {

	}
	```

- StdMngApplication.java 수정

	```java
	@SpringBootApplication
	public class StdMngApplication implements CommandLineRunner {

		@Autowired
		private StudentRepository studentRepository;

		// main 생략

		@Override
		public void run(String... args) throws Exception {
			Student s1=Student.builder().name("Sunio").address("Hong Kong").email("sunio345@gmail.com").build();
			Student s2=Student.builder().name("Gian").address("Hokaido").email("gian445@gmail.com").build();
			Student s3=Student.builder().name("성명건").address("한국").email("hugoMGSung@gmail.com").build();
			Student s4=Student.builder().name("Sizuka").address("Tokyo").email("sizuka3aa45@gmail.com").build();
			Student s5=Student.builder().name("Doraemon").address("Japan").email("doraemon345@gmail.com").build();
		
			List<Student> studentList=List.of(s1,s2,s3,s4,s5);
			studentRepository.saveAll(studentList);
		}
	}
	```

- 실행

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0325.png" width="800">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0326.png" width="800">

- 결과

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0327.png" width="800">


### C. 구현 작업
- /controller/StudentController.java 작업

	```java
	@Controller
	public class StudentController {
		@Autowired
		private StudentRepository studentRepository;

		@GetMapping("/home")
		public String homePage(Model mv) {
			mv.addAttribute("studentlist", studentRepository.findAll());
			return "home";
		}
	}
	```

- /resource/templates/home.html 작업
	- 생략

- 실행결과

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0328.png" width="800">


