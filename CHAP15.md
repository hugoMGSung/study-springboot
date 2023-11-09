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


[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP16.md)
