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
9. Linux 콘솔에서 git 설치 및 폴더 생성
	```shell
	> sudo yum install git
	> git --version
	> mkdir webapp
	> cd webapp
	```

10. git 명령으로 소스 내려받기
	```shell
	> git clone https://github.com/hugoMGSung/springaws.git
	> cd springaws
	```

11. https://gradle.org/install/#manually 에서 Download 링크를 새 탭으로 오픈

12. direct link 의 링크주소 복사

13. AWS 콘솔에서 다음과 같이 입력
	```shell
	> sudo wget https://services.gradle.org/distributions/gradle-8.4-bin.zip
	```
	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0146.png" width="600">

14. 압축파일을 처리할 작업 진행
	```shell
	> sudo mkdir /opt/gradle
	> sudo unzip -d /opt/gradle/ gradle-8.4-bin.zip
	> ls /opt/gradle/gradle-8.4/
	LICENSE  NOTICE  README  bin  init.d  lib
	```
15. gradle path 지정
	```shell
	> export PATH=$PATH:/opt/gradle/gradle-8.4/bin
	> gradle -v
	```

16. 스프링 위치로 이동
	```shell
	> cd webapp/springaws
	```

17. gradle wrapper 실행

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0148.png" width="600">

11. gradlew 권한 변경 실행
	```shell
	> sudo chmod 777 ./gradlew
	```

12. gradlew 빌드 실행
	```shell
	> ./gradlew build
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0149.png" width="600">

13. /build/libs 에 jar파일 확인
14. java -jar jar파일명으로 프로젝트 실행
	```shell
	> java -jar app-ec2-0.0.1-SNAPSHOT.jar
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0150.png" width="600">

	실행확인 후...

15. 할당받은 public 아이피로 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0151.png" width="600">


### F. AWS RDS 서비스
데이터베이스 클라우딩 서비스

1. RDS 선택

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0152.png" width="600">

2. 데이터베이스 생성 버튼 클릭
3. 표준생성, MariaDB 선택, 템플릿 프리티어, DB인스턴스 식별자명 지정, 

	자격증명설정에 마스터 아이디/패스워드 입력(/, ', ", @ 사용불가)

4. 퍼블릭 액세스 허용
5. 데이터베이스 생성 클릭 -> 시간 소요

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0153.png" width="600">
6. 상세에서 VPC 보안 그룹 링크 클릭

7. 인바운드 규칙 편집으로 MYSQL/Aurora , AnyWhere IPV4 규칙저장
8. HeidiSQL 연결확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0154.png" width="600">

9. RDS 파라미터 그룹 클릭
10. 기본값의 default.mariadb.xx 클릭

	- time_zone : Asia/Seoul
	- character_set_client, character_set_filesystem, character_set_conneciton : utf8 지정
	- chracter_set_results, chracter_set_database, chracter_set_server : utf8 지정

11. 수정 마지막에 즉시적용, RDS 인스턴스 재부팅 선택

12. 새 계정 추가
	```sql
	-- DB 생성
	CREATE DATABASE webdb;
	-- 사용자 생성
	CREATE USER 'webuser'@'%' IDENTIFIED BY 'webuser';
	-- 권한 설정
	GRANT ALL PRIVILEGES ON webdb.* TO 'webuser'@'%';
	-- 또는 각자로 실행
	GRANT SELECT, INSERT, DELETE, CREATE, UPDATE, ALTER, DROP PRIVILEGES ON webdb.* TO 'webuser'@'%';
	```

13. webuser로 로그인 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0155.png" width="600">


### G. RDS 연동
1. build.gradle에 Spring Data JPA, MariaDB Driver, Spring Security, Web 추가
2. application.properties에 AWS RDS DB 설정 추가
3. /test/.../AppRdsApplicaitonTest.java 작성
4. /controller/TimeController.java 작성
5. /config/CustomSecurityConfig.java 와 CustomServletConfig.java 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0156.png" width="600">

~~여기서부턴 다시~~

### H. AWS S3 서비스
스토리지 서비스

1. S3 선택
2. 버킷 만들기 클릭
3. 버킷 이름 작성, 액세스 설정 외부 가능 선택 버킷 만들기
4. 만들어진 버킷 클릭, 권한 탭 이동
5. 버킷 권한 > 버킷 정책 > 편집 클릭
6. 정책 생성기 클릭
7. 정책 설정
	- Select Type of Policy : S3 Bucket Policy
	- Principal : *
	- Action : GetObject, PutObject, DeleteObject 선택
	- ARN : arn:aws:s3:::hugo-sb-bucket/* (/* 중요!!!)
8. Add Statement 버튼 클릭 후, Generate Policy 클릭

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0157.png" width="600">

9. 위의 생성된 json 복사하여 버킷 정책에 붙여넣고, 변경사항 저장 클릭

### I. 버킷 테스트
1. 업로드 버튼 클릭
2. 파일 추가 후 업로드
3. 이미지 링크 클릭하면 객체 URL 표시

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0158.png" width="600">

4. AWS에서 IAM 클릭, IAM 대시보드 메뉴 사용자 클릭, 사용자 생성
5. 다음에서 직접 정책 연결 선택 후 AmazonS3FullAccess 선택
6. 보안자격증명에서 콘솔 로그인 링크 콘솔 액세스 활성화


	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0160.png" width="500">

7. csv 파일 다운로드
8. S3 서비스 복귀, 권한 탭 이동 객체 소유권 편집
9. ACL 활성화 변경사항 저장 클릭
10. 계정 > 보안자격 증명 클릭, 액세스 키 클릭
11. 액세스 키 csv도 다운로드

### J. SpringBoot S3 접근
1. build.gradle에 라이브러리 추가
	```tex
	implementation 'org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE'
    implementation 'net.coobird:thumbnailator:0.4.16'
	```

2. applicaiton.properties에 AWS accesskey및 secretkey 추가 등 설정
3. /util/LocalUploader.java, S3Uploader.java 작성
4. /test/.../util/S3UploaderTests.java 작성, 테스트


... 다시

[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP17.md)