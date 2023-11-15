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
18. /test/.../repository/MemberRepositoryTests.java 작성 테스트
19. /securityy/dto/MemberSecurityDTO.java 작성 (다른 dto와 다름!!)
20. service/MemberService.java, MemberServiceImpl.java 작성
21. MemberController join() 메서드 변경
 

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0104.png" width="600">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0105.png" width="600">

22. security/CustomUserDetailService 수정


	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0106.png" width="600">

23. /member/login.html의 password 입력 type을 password로 변경

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
구글, 카카오, 네이버의 로그인을 자신의 웹사이트에 적용하여 인증을 공유하는 방식

~~1. https://developers.kakao.com/ 애플리케이션 추가~~
~~2. 플랫폼 웹 등록버튼 클릭 후 http://localhost:8080 입력~~
~~3. 카카오 로그인 항목 클릭 후 온~~
~~4. 아래 Redirect URI 지정 - http://localhost:8080/login/oauth2/code/kakao 로 지정~~
~~5. 보안 클릭 후 Client Secret 에서 코드 생성 클릭~~

~~6. build.gradle 에 OAuth2 Client 라이브러리 추가~~
~~7. application.properties에 OAuth2 관련 설정 추가~~
~~8. config/CustomSecurityConfig filterChain() 에 oauth2Login() 추가~~
~~9. templates/member/login.html 로그인 링크 추가~~
~~10. 단, 로그인 후 리다이렉트 안됨~~
~~11. security/CustomOAuth2UserService.java 작성~~

현재 카카오 API 에 문제로 제대로 동작안함. 구글 로그인으로 변경. 

1. https://console.cloud.google.com/ 접속
2. 새 프로젝트 생성 Hugo-OAuth-login 입력 후 만들기, 만든 프로젝트 선택
3. 대시보드 클릭 > API 개요로 이동 
4. API 및 서비스 화면 메뉴 중 라이브러리 클릭

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0171.png" width="600">

5. Google+ API 선택 후 사용 클릭
6. OAuth consent screen(동의 화면) 클릭
7. User Type 외부 선택 후 '만들기'
8. 앱정보 > 앱 이름, 사용자 지원 이메일(기본), 로고 등 입력 

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0172.png" width="600">

9. 앱 도메인은 현재 http://localhost:8080 입력, 개발자 연락처 정보 입력 후 '저장 후 계속' 버튼 클릭

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0173.png" width="600">

10. 범위에서 '범위 추가 또는 삭제' 버튼 클릭

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0175.png" width="600">
	
11. 나머지 화면은 계속 진행, '대시보드로 돌아가기' 버튼일 때 멈춤
12. 사용자 인증정보 메뉴 클릭
13. '+ 사용자 인증 정보 만들기' 링크 클릭 > OAuth 클라이언트 ID 클릭
14. 아래와 같이 입력
	- 애플리케이션 유형: 웹 애플리케이션 선택
	- 이름 입력
	- 승인된 자바스크립트 원본 '+ URI 추가' 클릭
	- 현재는 http://localhost:8080 입력 추후 AWS아이피나 도메인으로 변경 가능

		만약 도메인을 신청했다면, http://www.hugo83.com 과 http://hugo83.com 을 전부 입력해야 함
	
	- 승인된 리디렉션 URI 는 현재 개발중인 http://localhost:8080/login/oauth2/code/google 입력

	- '만들기' 클릭

15. OAuth 클라이언트 생성 메시지 팝업

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0174.png" width="400">

### F. 소셜 로그인(OAuth2) Spring Boot 연동
1. build.gradle에 라이브러리 추가
2. application.properties에 google 설정 추가
	```tex
	spring.security.oauth2.client.registration.google.client-id=클라이언트 ID
	spring.security.oauth2.client.registration.google.client-secret=클라이언트 보안 비밀
	spring.security.oauth2.client.registration.google.scope=profile,email
	```

3. /config/CustomSecurityConfig.java 설정에 oauth2Login() 추가
4. /templates/member/login.html에 구글링크 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0176.png" width="600">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0177.png" width="600">

5. /security/CustomOAuth2UserService.java 기본 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0178.png" width="600">

6. /security/CustomOAuth2UserService.java loadUser() 메서드 추가작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0179.png" width="600">

7. /repository/MemberRepository.java 내용 수정
8. /security/dto/MemberSecurityDTO.java OAuth2User 인터페이스 추가
9. /security/CustomOAuth2UserService.java 에서 같은 이메일 사용자가 없으면 자동으로 회원가입하고 MemberSecurityDTO 반환

10. /security/CustomSocialLoginSuccessHandler.java 작성
11. /config/CustomSecurityConfig.java 에 위 핸들러 추가
12. /templates/members/modify.html 작성
13. /controller/MemberController.java 에 /modify GetMapping 메서드 작성
14. /service/MemberService.java 에 modify() 추가
15. /service/MemberServiceImpl.java에 modify() 구현
16. /modify Post 메서드 작성

17. 테스트.... 현재 이미지에 _ 들어가면 문제 발생(오류 수정요)

[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP15.md)
