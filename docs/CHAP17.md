# study-springboot
스프링부트 저장소 다시 만들기

## chap17. SpringBoot AWS
아마존 웹서비스 운영 계속

### A. AWS S3 서비스
스토리지 서비스

1. S3 버킷 만들기
2. 버킷 이름 입력
3. 이 버킷의 퍼블릭 액세스 차단 설정 > 모든 퍼블릭 액세스 차단 해제

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0161.png" width="600">

4. AWS Console > IAM > 액세스 관리 > 사용자 > 사용자 추가 클릭
5. 사용자 명 입력 후 다음 클릭
6. 직접 정책 연결을 클릭, AmozonS3FullAccess 를 선택하고 다음
7. 사용자 생성 클릭
8. 생성한 사용자 이름 링크 클릭
9. 보안자격 증명 탭 클릭 후 액세스 키 만들기
10. AWS 외부에서 실행되는 애플리케이션 선택 후 다음
11. 태그 입력 후 액세스 키 만들기 클릭
12. .csv 파일 다운로드 보관할 것

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0163.png" width="600">

### B. Spring Boot 연동 테스트
1. 프로젝트 생성
2. build.gradle에 의존성 추가
3. application.properties 에 S3 설정 추가
3. /config/S3Config.java 작성
4. /controller/FileUploadController.java 작성
5. PostMan 에서 API 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0164.png" width="600">

6. AWS S3 버킷 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0165.png" width="600">

7. 현재 이미지 링크를 클릭해도 접근불가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0166.png" width="600">

8. 버킷 권한 > 버킷 정책 > 편집 클릭
9. 버킷 정책 > 버킷 ARN 복사
10. 정책 생성기 클릭
11. Select Type of Policy : S3 Bucket Policy 선택
	- Principal : *
	- Actions : Get Object / Put Object / Delete Object 선택
	- ARN : MyARN_string + /* (중요!!)
	- Add Statement 클릭
12. Policy Statment 확인 후 Generate Policy 클릭

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0167.png" width="600">
13. 생성된 json string 복사 정책에 붙여넣고 변경사항 저장 버튼 클릭

14. 책과 동일하게 /util/S3Uploader.java 작성
15. /test/.../util/S3UploaderTests.java 작성
16. 테스트 시 올라가지 않음
17. S3 서비스 > 권한 > ACL(액세스 제어 목록) 에서 버킷 소유자 적용 링크 클릭
18. ACL 활정화됨 변경 후 변경사항 저장
19. 다시 테스트 후 업로드 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0168.png" width="600">

20. S3UploaderTests.java에 삭제 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0169.png" width="600">
	
	최초 파일 수

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0170.png" width="600">

	테스트에서 mountain.jpg 삭제 후


### C. 컨트롤러를 통한 업로드 처리, S3 연동
1. build.gradle 썸네일 라이브러리 추가
2. application.properties에 로그 등 추가 설정

~~아래에서 부터 S3연동이나 EC2 VPC 내부에 저장하는 방식은 생략~~
3. /util/LocalUploader.java 작성
4. ...

[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP91.md)
