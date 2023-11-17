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
4. https://themewagon.com/themes 에 있는 mazer admin dashboard templates 다운로드

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0205.png" width="600">

5. https://www.flaticon.com/ 에서 Book 검색 book-stack.png 다운로드
6. https://convertio.co/kr/png-ico/ 에서 book-stack.png를 업로드, 아이콘으로 변환, 다운로드 book.ico로 이름 변경
7. mazere admin ... templates를 압축 해제 resources/static 아래에 js, css, images 등 폴더 배치
8. /static/index.html, table-datatable.html 붙여넣기

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0206.png" width="600">

	디자인 깨짐

9. Ctrl + H : assets/ -> ./ 로 모두 변경

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0207.png" width="600">

10. /resources/templates/layouts/admin_base.html 생성 (mazer/table-datatable.html 내용 붙여넣기)
11. admin_base.html에 Thymeleaf 구문 추가하기

### D. 구현 시작
1. /controller/IndexController.java 작성
2. /controller/BooksController.java 작성
3. /resources/templates/books/list.html 기본 틀 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0208.png" width="600">

4. list.html에 admin_base.html에서 주석처리한 card div 옮기기

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0209.png" width="600">

5. /controller/BooksController.java에 /books/create 메서드 작성하기
6. 책 등록화면에 들어갈 컬럼 확인
	- 책제목 / 부제목 / 추가제목 / 저자 / 번역 / 출판 / 발행 / 책소개 / 카테고리
	- 쪽수 / ISBN / 책이미지 / 목차 / 기타...

7. /resources/templates/books/create.html 기본 틀 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0210.png" width="600">




