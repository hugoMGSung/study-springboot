# 구글 소셜 로그인

## 순서
1. 프로젝트 생성 및 의존성 추가
2. SecurityConfig.java 추가
3. 구글 OAuth2 신청
4. application.properties 설정 추가
5. CustomOAuth2UserService 구현 중
6. dto.OAuth2Response 생성

    ```json
    // 구글데이터 
    {
        resultcode=00, messagae=success, id=123456789, name=XXX
    }

    // 네이버데이터 
    { 
        resultcode=00, messagae=success, response={ id=123456789, name=XXX }
    }
    ```

7. dto.GoogleResponse / NaverResponse 등 추가
8. SecurityConfig.java에
    - private final CustomOAuth2UserService customOAuth2UserService; 추가
    - .userInfoEndpoint(uiep -> uiep.userService(customOAuth2UserService)) 로 oauth2login 변경


9. dto.UserDto 생성
10. dto.CustomOAuth2User 생성
11. 로그인 테스트
12. MemberEntity 작성
13. MemberRepository 작성
14. CustomOAuth2UserService 에 유저 정보 DB저장 로직 작성

15. 커스텀 로그인 페이지 작성

16. 세션처리 - TODO