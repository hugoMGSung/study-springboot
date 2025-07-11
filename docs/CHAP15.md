# study-springboot
스프링부트 저장소 다시 만들기

## chap15. API서버 / JWT
REST API와 JWT

### A. 프로젝트 생성
1. 명령 팔레트(Ctrl+Shift+P) 로 새롭게 프로젝트 생성
2. api01로 만들 폴더, 탐색기에서 chap15로 변경

3. Lombok 테스트 라이브러리, ModelMapper 라이브러리, REST용 라이브러리 추가
4. application.properties DB관련 정보와 관련설정

5. main/java/.../config/CustomSecurityConfig 클래스 작성
6. /config/RootConfig 작성
7. /config/CustomServletConfig.java 작성
8. /config/SwaggerConfig.java 작성

9. /controller/SampleController.java 작성 후 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0112.png" width="600">

10. /swagger-ui/index.html 테스트 

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0113.png" width="600">

11. /resources/static/sample.html 에 Ajax 작성 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0114.png" width="600">

12. /domain/APIUser.java 클래스 작성
13. /repository/APIUserRepository.java 인터페이스 작성
14. /test/.../repository/APIUserRepositoryTests.java 작성 후 testInserts() 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0115.png" width="400">

15. /security/APIUserDetailsService.java 클래스 작성
16. /dto/APIUserDTO.java 클래스 작성
17. /security/APIUserDetailsService.java loadUserByUsername() 내 return null; 변경

### B. 토큰인증을 위한 시큐리티 필터

사용자가 자신의 mid/ mpw 를 이용 Access Token과 Refresh Token을 얻는 단계

사용자가 Access Token을 이용, 컨트롤러를 호출하고자 인증/권한 체크 기능

1. /security/filter/APILoginFilter.java 작성
2. /config/CustomServletConfig.java 에 AbstractAuthenticationProcessingFilter 설정

	localhost:8080/generateToken 호출결과

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0117.png" width="500">

3. JSON 처리를 위한 build.gradle 에 라이브러리 추가
4. /security/filter/APILoginFilter.java POST 방식요청 JSON 문자열 처리 부분 추가
5. /resources/static/apiLogin.html 작성

6. /handler/APILoginSuccessHandler.java 작성
7. CustomSecurityConfig.java 에 APILoginSuccessHandler 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0118.png" width="500">

8. build.gradle 에 jjwt 라이브러리 추가
9. /util/JWTUtil.java 작성 (JWT 사용 편의용)
10. application.properties에 서명 추가
11. /test/.../util/JWTUtilTests.java 작성 및 테스트
12. /util/JWTUtil.java generateToken() 수정
13. testGenerate() 재테스트 JWT 문자열 준비.

	```tex
	com.hugo83.api01.util.JWTUtilTests       : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTk1OTExMDEsIm1pZCI6IkFCQ0RFIiwiaWF0IjoxNjk5NTkxMDQxfQ.eKD0nXOTPfidGs07Cu2cZSNFvJ0HwCMo0U-Six4jumk
	```

14. 생성된 JWT 문자열 https://jwt.io 에서 확인

	VERIFY SIGNATURE에 비밀키 hello1234567890 먼저 입력 후 생성된 JWT 문자열을 Encoded에 붙여넣고 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0119.png" width="500">

15. JWTUtil의 validateToken() 수정
16. JWTUtilTest.java에 testValidate() 작성 후 테스트(1분지나면 오류남)

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0120.png" width="500">

17. JWTUtil의 generateToken() 의 유효기간 변경 / testAll() 작성 후 테스트

### C. Access Token 발행
1. /security/handler/APILoginSuccessHandler.java 수정
2. /config/CustomSecurityConfig.java 에 JWTUtil 주입
3. /apiLogin.html에서 토큰 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0122.png" width="600">

4. https://jwt.io 에서 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0121.png" width="600">

5. /security/filter/TokenCheckFilter.java 작성
6. /config/CustomSecurityConfig 에 TokenCheckFilter 설정 지정

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0123.png" width="500">


7. /security/exception/AccessTokenException.java / RefreshTokenException.java 작성 
8. TokenCheckFilter에 AccessToken 검증용 validateAccessToken() 메서드 추가

9. /config/SwaggerConfig.java (문제 구형 Swagger와 최신 Swagger에서는 동작안함)[X]
10. /security/filter/RefreshTokenFilter.java 작성
11. CustomSecurityConfig 클래스 코드 수정
12. /resources/static/apiLogin.html 내용 수정
13. /resources/static/refreshTest.html 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0124.png" width="600">

14. RefreshTokenFilter의 doFilterInternal() 내부 수정

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0125.png" width="600">

15. /resources/static/sample.html 작성
16. /resources/static/sendJWT.html 작성

	작업중 쿠키 삭제후 클릭하면 'Cannot Find Access Token' 팝업 

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0126.png" width="600">

### D. Ajax / CORS 설정
1. https://nginx.org/en/download.html 에서 Stable Version 다운로드 / 압축해제
2. Nginx 사용 명령어
	- nginx -s stop
	- nginx -s quit
	- nginx -s reload
	- nginx -s reopen
	- start nginx

3. static 폴더내 html 파일을 nginx html 내에 복사/붙여넣기
4. localhost/apiLogin.html 로 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0127.png" width="600">

5. /config/CustomSecurityConfig 내 cors() 설정 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0128.png" width="600">

#### D-1. Todo API 서비스
6. build.gradle 내 DB처리 및 기타 설정 추가
7. /domain/Todo.java 클래스 작성
8. Gradle > build > clear -> build -> other > compileJava

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0129.png" width="300">

9. /repository/TodoRepository.java 작성
10. /dto/TodoDTO.java 작성
11. /dto/PageResponseDTO.java, PageRequestDTO.java 작성
12. /repository/search/TodoSearch.java, TodoSearchImpl.java 작성
13. /test/.../repository/TodoRepositoryTests.java 작성 테스트 데이터 생성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0130.png" width="500">

14. /service/TodoService.java, TodoServiceImpl.java 작성
15. /controller/TodoController.java 작성
16. localhost:8080/swagger-ui/index.html 에서 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0131.png" width="600">

17. /security/filter/TokenCheckFilter.java 추가 주입
18. CustomSecurityConfig.java 수정

19. "Token is null or too short" 문제해결 요

[Next](./CHAP16.md)
