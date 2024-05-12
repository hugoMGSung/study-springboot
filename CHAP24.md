# study-springboot
스프링부트 저장소 다시 만들기(with Oracle)

## chap24. Spring Boot tutorial

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
