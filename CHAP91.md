# study-springboot
스프링부트 저장소 다시 만들기

## chap91. SpringBoot ToyProject
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
4. https://themewagon.com/themes/dashmin-responsive-free-bootstrap-5-html5-admin-dashboard-template/  에 있는 무료 templates 다운로드

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0220.png" width="600">

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

### D. 구현 시작 - 도서 데이터 조회 및 입력
1. /controller/IndexController.java 작성
2. /repository/BooksRepository.java 인터페이스 작성
3. TinylibraryApplication.java 에 
3. /test/.../repository/BooksRepositoryTests.java 작성, 테스트
4. /config/RootConfig.java 작성
5. /dto/BookDTO.java 작성
6. /service/BookService.java 인터페이스 작성
7. /service/BookServiceImpl.java 에 메서드 구현
8. /test/.../service/BookServiceTests.java 작성, 테스트
9. /dto/PageRequestDTO.java, PageResponseDTO.java 작성

10. /controller/BooksController.java 작성
11. /resources/templates/books/list.html 작성(admin_base.html 사용)

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0222.png" width="600">

12. /resources/templates/books/list.html 에 register 버튼 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0223.png" width="600">

13. /controller/BooksController.java에 /books/register 메서드 작성하기
14. 책 등록화면에 들어갈 컬럼 확인
	- 책제목 / 부제목 / 추가제목 / 저자 / 번역 / 출판 / 발행 / 책소개 / 카테고리
	- 쪽수 / ISBN / 책이미지 / 목차 / 기타...

15. /resources/templates/books/register.html 기본 틀 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0210.png" width="600">

16. register.html에 @Valid의 에러메시지 처리 추가
17. /dto/BookDTO.java에 @NonNull, @NotBlank 어노테이션 추가. releaseDate에 어노테이션은 오류 발생
18. /books/register 등록 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0224.png" width="600">

8. /entity/BaseEntity.java 작성
9. /entity/Book.java 작성
10. /TinylibraryApplication.java 에 @EnableJpaAuditing 어노테이션 추가
11. application.properties, spring.jpa.hibernate.ddl-auto=create-update로 변경 후 서버 재시작 

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0211.png" width="600">

12. /repository/BookRepository 인터페이스 작성

13. /test/.../repository/BookRepositoryTests.java 작성 

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0212.png" width="600">

13. /dto/BookDTO.java 작성
14. /service/BookService.java 인터페이스 작성
15. /service/BookServiceImpl.java 클래스 작성
16. /controller/BooksController.java에 코드 서비스 객체 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0213.png" width="600">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0214.png" width="600">


### E. 구현 - 검증 추가
1. build.gradle에 검증 라이브러리 추가
2. /dto/BookDTO.java에 검증 어노테이션 추가
3. /controller/BookController.java 수정
4. /resources/templates/books/create.html 수정

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0215.png" width="600">

### F. 구현 - 상세화면 구현
1. /dto/BookReadDTO.java 작성 - 조합하여 새로 묶은 DTO 사용해도 무방
2. /service/BookService.java read() 메서드 추가
3. /service/BookServiceImpl.java 에 read() 메서드 구현
4. /test/.../service/BookServiceImplTest.java 작성, 테스트
5. /controller/BooksController.java에 읽기 메서드 추가
6. /resources/.../books/read.html 작성 (create.html 활용)

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0216.png" width="600">

7. /templates/common/error/422.html 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0217.png" width="600">

### G. 구현 - 편집화면 및 기능 구현
1. /service/BookService.java 에 edit() 추가
2. /service/BookServiceImpl.java에 edit() 구현
3. /controller/BooksController.java에 코드 추가
4. /resources/.../books/edit.html 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0218.png" width="600">

5. /entity/Book.java에 change() 메서드 추가
6. /service/BookService.java update() 추가
7. /service/BookServiceImpl.java 에 update() 추가
8. /controller/BooksController.java에 /edit POST 메서드 작성

