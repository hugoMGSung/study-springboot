# study-springboot
스프링부트 저장소 다시 만들기

## chap14. API서버 / JWT
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


[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP16.md)
