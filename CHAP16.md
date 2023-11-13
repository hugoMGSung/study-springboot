# study-springboot
스프링부트 저장소 다시 만들기

## chap16. SpringBoot AWS
아마존 웹서비스 운영

### A. AWS 가입 및 서비스 구성
1. https://aws.amazon.com/ 접속, AWS 프리티어로 시작 클릭

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0133.png" width="600">
2. 신용카드 결재정보 입력

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0135.png" width="600">

3. Support 플랜 선택

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0136.png" width="600">

	프리티어도 사용하면 비용이 발생할 수 있으므로 주의할 것!

### B. EC2 생성 및 접속
1. AWS 콘솔 홈에서 EC2 클릭
2. 인스턴스 시작 버튼 클릭

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0138.png" width="500">

3. 이름 및 태그에 지정이름 입력 후, Amazon Linux 선택확인
4. 인스턴스 유형, t2.micro 선택 확인
5. 키패어 이름 - 필수 에 새 키패어 생성 링크 클릭 (Hugo83) 후 pem 파일 다운로드 확인

	터미널 환경 접속시 사용

6. 우측 하단 인스턴스 시작 버튼 클릭

7. 생성 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0139.png" width="600">

8. 인스턴스 상세에서 인스턴스 ID 클릭
9. 인스턴스 요약 하단의 보안 탭에서 보안그룹 링크 클릭
10. 인바운드 규칙 편집 클릭
11. 규칙 추가 클릭하여 규칙 추가 후 저장

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0140.png" width="600">


### C. Putty를 이용한 터미널 연결
1. https://www.putty.org/ 에서 다운로드 후 설치
2. PuTTYgen 실행 후 내려받은 pem 로딩
3. Save private key 버튼으로 눌러 Hugo83.ppk 로 저장
4. Putty 실행 Host명에 EC2 인스턴스 퍼블릿 IPv4 DNS를 입력
5. Connection - SSH - Auth 항목 Hugo83.ppk 지정

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0141.png" width="500">

6. login as : ec2-user 입력

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0143.png" width="600">

### D. 운영환경 설정
1. yum list java*17*  로 설치할 수 있는 자바 버전 확인
2. sudo yum install java-17-amazon-corretto.x86_64 로 설치

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0144.png" width="600">
 
### E. Spring Boot 동작확인
1. 명령 팔레트로 새 프로젝트 새성
2. Spring Boot 버전은 다운그레이드
3. Arifact ID는 app-ec2 입력
4. /static/index.html 작성
5. /controller/SimpleController.java 작성
6. 로컬에서 실행확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0145.png" width="600">

7. 프로젝트를 깃허브에 올려놓은 다음 EC2에서 받은 뒤 빌드하는 것이 좋으므로 이전과는 달리 깃헙에 독립적으로 올리는 것을 추천(파일 용량)
8. AWS용 깃헙 새로 생성
