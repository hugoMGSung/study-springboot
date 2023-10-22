# study-springboot
스프링부트 저장소 다시 만들기

## chap07. Database 연동

### A. Maria DB 설치
0. MySQL 사용해도 무방함
1. https://mariadb.org/download/
2. MariaDB Server 11.1.2 다운로드 후 설치
3. 특정 위치에 설치
4. Use UTF8 as default server's character set 체크
5. TCP port: 3306 --> 3326 으로 변경
6. root 계정 패스워드 입력

### C. 데이터베이스 생성
1. webdb 로 DB 생성
2. 사용자 생성, webuser
3. webuser로 재연결
4. tbl_todo 테이블 생성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0027.png" width="600">

### B. 프로젝트 진행
1. chap07 추가 
	- 순서 생략...
2. build.gradle 변경
	- 내용 생략...
3. 외부 라이브러리 확인 후
	1. servlet이 추가되지 않는 오류가 발생 가능함
	2. 하단 빌드 탭의 Gradle 프로젝트 다시 로드(리사이클 모양) 아이콘 클릭 리로드 할 것
4. 데이터베이스 탭 클릭 후 +
5. 데이터 소스 > MariaDB 클릭
6. 데이터 소스 및 드라이버 창
	1. 포트: 3306
	2. 사용자: webuser / 비밀번호: 추가
	3. 데이터베이스: webdb
7. 누락된 드라이버 다운로드 클릭
8. 연결 테스트 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0028.png" width="400">

9. https://mvnrepository.com 에서 MariaDB Java Client 검색 Gradle(Short) 입력박스 값 복사
10. build.gradle, dependencies에 추가
	```tex
	// https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client
	implementation 'org.mariadb.jdbc:mariadb-java-client:3.2.0'
	```
11. HikariCP 설정
	```tex
	implementation 'com.zaxxer:HikariCP:5.0.1'
	```

### C. 테스트 방법
1. test/java 아래 com.hugo83 패키지 추가
2. test/java/.../ConnectTest 클래스 추가
3. 실행 > ConnectTest.java 디버그 클릭
4. 테스트 통과: 2 확인

### D. DB처리 라이브러리
1. Lombok 라이브러리 build.gradle에 추가
	```tex
	implementation 'org.mariadb.jdbc:mariadb-java-client:3.2.0'     // mariadb

	implementation 'com.zaxxer:HikariCP:5.0.1'                  // HikraiCP
	implementation 'org.modelmapper:modelmapper:3.2.0'          // ...

	compileOnly group: 'org.projectlombok', name: 'lombok', version: '1.18.30'  // Lombok
	annotationProcessor group: 'org.projectlombok', name: 'lombok', version: '1.18.30'

	testCompileOnly group: 'org.projectlombok', name: 'lombok', version: '1.18.30'
	testAnnotationProcessor group: 'org.projectlombok', name: 'lombok', version: '1.18.30'

	implementation group: 'org.apache.logging.log4j', name: 'log4j-core', version: '2.21.0' // log4j
	implementation group: 'org.apache.logging.log4j', name: 'log4j-api', version: '2.21.0'

	implementation group: 'org.apache.logging.log4j', name: 'log4j-slf4j-impl', version: '2.21.0'
	```
2. main/java/.../domain/TodoVO 클래스 생성 후 작성
3. main/java/.../dao 패키지 추가
4. 아래 ConnectionUtil enum 추가 후 작성
5. TodoDAO 클래스 추가 후 작성
6. main/java/.../dto 패키지에 TodoDTO 클래스 추가 후 작성 (DAO와 DTO 두 개를 만드는 이유)
7. main/java/.../util/MapperUtil enum 추가, 작성
8. main/java/.../service/TodoService enum 추가, 작성
9. test/java/.../service/TodoServiceTest 클래스 추가, 작성
10. TodoServiceTest 테스트 실행 확인
11. main/java/.../resources/log4j2.xml 설정파일 생성, 작성

### E. Todo DB연동 
1. main/java/.../controller/TodoListController.java 생성, 작성
2. MariaDB 테이블에 데이터 추가
3. 톰캣 서버 추가
	- VM 옵션: -Dfile.encoding=UTF-8
4. main/webapp/index.jsp 생성, 작성
5. main/webapp/WEB-INF/todo/list.jsp 생성, 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0029.png" width="600">

