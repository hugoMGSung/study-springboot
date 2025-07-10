# 구글 소셜 로그인

## 순서
1. 프로젝트 생성 및 의존성 추가
2. SecurityConfig.java 추가
3. 구글 OAuth2 신청
4. application.properties 설정 추가
5. OAuth2UserService 구현
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

7. dto.UserDto 생성
8. dto.CustomOAuth2User 생성
9. 로그인 테스트
10. MemberEntity 작성
11. MemberRepository 작성
12. CustomOAuth2UserService 에 유저 정보 DB저장 로직 작성

13. 커스텀 로그인 페이지 작성

13. 세션처리 - TODO