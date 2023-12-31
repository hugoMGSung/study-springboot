# study-springboot
스프링부트 저장소 다시 만들기

## chap92. SpringBoot ToyProject
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
		// Thymeleaf 레이아웃
		implementation 'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.1.0'
	}
	```
2. Gradle build
3. application.properties 작성
~~4.https://themewagon.com/themes/dashmin-responsive-free-bootstrap-5-html5-admin-dashboard-template/  에 있는 무료 templates 다운로드~~

	~~<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0220.png" width="600">~~

4. 계속 js 오류가나서 https://adminkit.io/ 로 변경. 아래 내용은 동일하므로 사진 변경안함

5. https://www.flaticon.com/ 에서 Book 검색 book-stack.png 다운로드
6. https://convertio.co/kr/png-ico/ 에서 book-stack.png를 업로드, 아이콘으로 변환, 다운로드 book.ico로 이름 변경
7. admin templates를 압축 해제 resources/static 아래에 js, css, images 등 폴더 배치
8. /static/index.html 등 붙여넣기

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0206.png" width="600">

9. Ctrl + H : assets/ -> ./ 로 모두 변경

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0207.png" width="600">

10. /resources/templates/layouts/admin_base.html 생성 (mazer/table.html 내용 붙여넣기)

	- layout:fragment 헤더 스크립트, 메인, 하단 스크립트 추가
	- href, src를 th:href, th:src 로 변경
	- Bootstrap 기능 추가하기 (refered by https://getbootstrap.com/docs/5.3/getting-started/introduction/)

### D. 구현 시작 - 도서 데이터 조회 및 입력
1. /entity/BaseEntity.java 작성
2. /entity/Book.java 작성
3. application.properties, spring.jpa.hibernate.ddl-auto=create-update로 변경 후 서버 재시작 

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0211.png" width="600">

4. /controller/IndexController.java 작성
5. /repository/BooksRepository.java 인터페이스 작성
6. TinylibraryApplication.java 에 
7. /test/.../repository/BooksRepositoryTests.java 작성, 테스트
8. /config/RootConfig.java 작성
9. /dto/BookDTO.java 작성
10. /service/BookService.java 인터페이스 작성
11. /service/BookServiceImpl.java 에 메서드 구현
12. /test/.../service/BookServiceTests.java 작성, 테스트
13. /dto/PageRequestDTO.java, PageResponseDTO.java 작성

14. /controller/BooksController.java 작성
15. /resources/templates/books/list.html 작성(admin_base.html 사용)

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0222.png" width="600">

16. /resources/templates/books/list.html 에 register 버튼 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0223.png" width="600">

17. /controller/BooksController.java에 /books/register 메서드 작성하기
18. 책 등록화면에 들어갈 컬럼 확인
	- 책제목 / 부제목 / 추가제목 / 저자 / 번역 / 출판 / 발행 / 책소개 / 카테고리
	- 쪽수 / ISBN / 책이미지 / 목차 / 기타...

19. /resources/templates/books/register.html 기본 틀 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0210.png" width="600">

20. register.html에 @Valid의 에러메시지 처리 추가
21. /dto/BookDTO.java에 @NonNull, @NotBlank 어노테이션 추가. releaseDate에 어노테이션은 오류 발생
22. /books/register 등록 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0224.png" width="600">

23. 검증 변경을 위해선 /books/register GetMapping 메서드에 Model model 파라미터 추가후 전부 변경해야 함!!

### F. 구현 - 상세화면, 수정, 삭제 구현
1. /controller/BooksController.java /books/read GetMapping 메서드 구현
2. /template/read.html 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0226.png" width="600">

3. /controller/BooksController.java read() 메서드 @GetMapping 어노테이션 수정
4. /template/modify.html 작성
5. /controller/BooksControoler.java remove() 메서드 작성
6. /modify.html에 삭제 모달창 추가, 변경

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0227.png" width="600">

7. /modify.html 모달창 커스터마이징 및 삭제 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0228.png" width="600">
	
### G. 첨부파일로 책 표지 저장 구현
1. build.gradle 에 첨부파일 관련 라이브러리 추가
2. application.properties 파일 설정
3. /entity/BookImage.java 작성
4. /entity/Book.java 에 @OneToMany 적용 및 이미지처리 추가 수정

5. build.gradle에 RestAPI 설정 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0229.png" width="600">

6. /controller/advice/CustomRestAdvice.java 작성
7. /controller/UpDownController.java 작성

	```java
	// 아래 파라미터 주시할 것!
	public List<UploadResultDTO> upload(@RequestParam("files") List<MultipartFile> uploadFiles) {
	```
8. 이미지 사이즈 확인 메서드 추가(시간 소요로 실패)
9. /test/.../repository/BooksRepositoryTests.java 이미지 업로드 메서드 작성, 테스트
10. BooksRepository @EntityGraph 추가 후 조회테스트 

11. /templates/books/register.html 에 파일표지 업로드 추가
12. /static/js/upload.js 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0230.png" width="600">

13. /dto/BookDTO.java 수정
14. /service/BookService.java 에 default 메서드 작성
15. /service/BookServiceImpl.java 메서드 내 수정
16. /test/.../service/BookServiceTests.java testRegisterWithImages() 메서드 작성, 테스트 확인
17. testReadAll() 메서드 작성, 테스트
18. /service/BookServiceImpl.java 메서드 내 modify() 수정
19. BooServiceTests.java 내 testModify() 작성, 테스트

### H. 리스트 및 상세에 책표지 표시
~~1. build.gradle 에 MyBatis 라이브러리 추가~~
~~2. application.properties 에 Mybatis 설정 추가~~
~~3. /mapper/BooksMapper.java 인터페이스 추가 ~~
~~4. /resources/mapper/BooksMapper.xml 생성~~
1. /templates/books/read.html 책 표지 나오도록 수정
2. /templates/books/modify.html 책 표지 출력과 수정용 모달창 표시
3. /controller/BookController.java 삭제처리 수정

### I. 회원관리 
1. application.properties에 Whiteabel Error Page 제거내용 추가
2. /templates/error 폴더에 404.html, 500.html 추가
3. build.gradle에 스프링시큐리티 라이브러리 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0231.png" width="600">

4. /config/CustomSecurityConfig.java 작성, SecurityFilterChain 리턴 filterChain() 작성
5. filterChain() 에 커스텀 로그인 부분 추가

	화면이 없어서 오류남

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0232.png" width="600">

6. /security/CustomUserDetailsService.java 작성
7. /controller/MemberController.java @GetMapping으로 /login, /join 작성
8. /entity/Member.java 작성, Book.java 내용 수정
9. /entity/MemberRole.java enum 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0233.png" width="500">

10. remember-me 용 테이블 생성(옵션)
11. /dto/MemberJoinDTO.java 작성
12. /security/dto/MemberSecurityDTO.java 작성
13. /security/CustomUserDetailService 수정(/login Post 없음)

14. /templates/member/join.html 작성
15. /repository/MemberRepository.java 작성
16. /service/MemberService.java 작성
17. /service/MemberServiceImpl.java 작성
18. /controller/MemberController.java @PostMapping /join 작성
19. /join.html 에서 회원가입 진행

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0234.png" width="400">

20. /template/mamber/login.html 작성
21. /controller/MemberController.java에 /logout 구현

[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP92.md)