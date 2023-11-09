# study-springboot
스프링부트 저장소 다시 만들기

## chap14. Spring Security
스프링 시큐리티 except OAuth2

### A. 기존 프로젝트 클론
1. chap13 복사 chap14 붙여넣기
2. 이전 프로젝트의 error/404.html 500.html 까지 구성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0102.png" width="600">

### B. 스프링 시큐리티 설정
1. build.gradle 에 라이브러리 추가
2. application.properties 설정 대신 config/CustomSecurityConfig 클래스 작성
3. 실행 후 /board/list로 접근하면 /login 으로 리다이렉트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0103.png" width="600">

4. 로그인 하지 않아도 볼 수 있도록 하려면 SecurityFilterChain 반환 filterChain=() 메서드 작성
5. application.properties에 로그레벨 설정
6. config/CustomSecurityConfig 소스 정적자원 스프링 시큐리티에서 제외 작성

#### B.1 인증과 권한
- 스스로를 증명(Authentication) / 로그인 / 아이디|패스워드 의미
- 인가(Authorization) / 접근할 수 있는 권한

7. config/CustomSecurityConfig 에 로그인 진행 설정.

	여기에 추가할 메서드는 deprecated 메서드임
	```java
	http.formLogin();
	```
8. security/CustomUserDetailsService.java 작성
9. controller/MemberController 작성
10. template/member/login.html 작성


11. dto/MemberJoinDTO 클래스 작성
12. controller/MemberController.java 에 join GET/POST 메서드 작성
13. template/member/join.html 작성
14. 403 에러 발생

15. security/handler/Custom403Handler.java 작성
16. config/CustomSecurityConfig의 filterChain() 메서드 추가 수정

17. repository/MemberRepository.java, MemberRole.java 작성 
18. service/MemberService.java, MemberServiceImpl.java 작성
19. MemberController join() 메서드 변경
 

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0104.png" width="600">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0105.png" width="600">

20. securityy/dto/MemberSecurityDTO.java 작성 (다른 dto와 다름!!)
21. security/CustomUserDetailService 수정


	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0106.png" width="600">

22. /member/login.html의 password 입력 type을 password로 변경

### C. 커스텀 로그인
1. templates/member/login.html 수정
2. controller/MemberController 수정
3. persistent_logins 테이블 생성
	```sql
	-- Spring Security에서 사용하므로 수정불가
	CREATE TABLE persistent_logins (
		username VARCHAR(64) NOT NULL,
		series VARCHAR(64) PRIMARY KEY,
		token VARCHAR(64) NOT NULL,
		last_used TIMESTAMP NOT NULL
	)
	```

4. config/CustomSecurityConfig 자동 로그인을 위한 설정변경
5. templates/member/login.html에 remember-me 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0107.png" width="500">

6. 웹브라우저 개발자도구에서 rememberme 키를 삭제하면 자동로그인 안됨

### D. 화면인증 처리
1. build.gradle에 Thymeleaf 라이브러리 추가
2. board/register.html javascrdipt에 const auth 추가
3. board/register.html Thymeleaf extra Springsecurity6 네임스페이스 추가
4. 작성 부분의 Writer 읽기 전용으로 변경

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0108.png" width="600">

5. controller/BoardController read() 어노테이션 추가 수정
6. templates/board/read.html 의 수정으로 사용자가 같을때만 Modify 버튼 활성화
7. controller/BoardController 의 modify() 어노테이션 추가 수정
8. controller/BoardController 의 remove() 어노테이션 추가 수정


### E. 소셜 로그인(OAuth2) 처리
1. https://developers.kakao.com/ 애플리케이션 추가
2. 플랫폼 웹 등록버튼 클릭 후 http://localhost:8080 입력
3. 카카오 로그인 항목 클릭 후 온
4. 아래 Redirect URI 지정 - http://localhost:8080/login/oauth2/code/kakao 로 지정
5. 보안 클릭 후 Client Secret 에서 코드 생성 클릭

6. build.gradle 에 OAuth2 Client 라이브러리 추가
7. application.properties에 OAuth2 관련 설정 추가
8. config/CustomSecurityConfig filterChain() 에 oauth2Login() 추가
9. templates/member/login.html 로그인 링크 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0111.png" width="600">

10. 단, 로그인 후 리다이렉트 안됨
11. security/CustomOAuth2UserService.java 작성

-- 문제 심각, Google OAuth2로 변경할 예정 나중에 하자...

[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP15.md)
