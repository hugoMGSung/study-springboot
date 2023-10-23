# study-springboot
스프링부트 저장소 다시 만들기

## chap08. 세션/쿠키/필터/리스너

### A. 시작!
1. chap08 이름으로 프로젝트 생성
2. build.gradle 편집
3. chap07 소스 그대로 복사/붙여넣기 
4. MariaDB 설정
5. Tomcat Server 설정
6. Tomcat Server 실행 후 웹사이트 동작 확인

### B. 세션사용 로그인
1. TodoRegisterController 클래스 doGet()메서드 수정
	```java
	HttpSession session = req.getSession(); // chap08 추가 시작
	if(session.isNew()) { //기존에 JSESSIONID가 없는 새로운 사용자
		log.info("JSESSIONID 쿠키가 새로 만들어진 사용자");
		resp.sendRedirect("/login");
		return;
	}

	//JSESSIONID는 있지만 해당 세션 컨텍스트에 loginInfo라는 이름으로 저장된 객체가 없는 경우
	if(session.getAttribute("loginInfo") == null) {
		log.info("로그인한 정보가 없는 사용자.");
		resp.sendRedirect("/login");
		return;
	} // chap08 추가 끝
	```
2. 서버 재시작 후 등록버튼을 누르면 /login 에러 발생

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0034.png" width="600">

3. tbl_member 테이블 생성
	```sql
	CREATE TABLE `tbl_member` (
		`mid` VARCHAR(50) NOT NULL DEFAULT NULL,
		`mpw` VARCHAR(50) NOT NULL DEFAULT NULL,
		`mname` VARCHAR(100) NOT NULL DEFAULT NULL,
		PRIMARY KEY (`mid`)
	)
	COLLATE='utf8mb3_general_ci'
	;
	```
4. 샘플 데이터 입력
5. main/java/.../domain/MemberVO.java 생성/작성
6. main/java/.../dao/MemberDAO 클래스 생성/작성
7. main/java/.../dto/MemberDTO 클래스 생성/작성
8. main/java/.../service/MemberService enum 생성/작성
9. main/java/.../controller/LoginController 클래스 생성/작성
10. 로그인 테스트, 실패시 화면

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0035.png" width="600">

11. list.jsp 에 세션확인 및 로그아웃 버튼 추가
12. main/java/.../controller/LogoutController 클래스 추가/작성

### C. 쿠키 연습(로컬 쿠키와 세션 쿠키 비교 필수!)
1. main/java/.../controller/TodoReadController 
	1. doGet() 메서드 수정
	2. findCookie() 메서드 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0037.png" width="700">

2. tbl_member 테이블에 uuid 컬럼 추가
3. UUID 관련 메서드 추가
	1. LoginController doPost()
	2. MemberDAO uuid관련 메서드
	3. MemberService 
4. main/java/.../filter/LoginCheckFilter.java 생성/작성
5. 로그인 시 auto 체크뒤 작업 후 Logout 이후 다시 등록을 누르면 로그인 없이 화면전환 됨(24시간 동안)
6. 참고: UTF-8 처리를 하지 않으면 한글 입력 오류
	chap07에서 메서드에 이 처리 영역을 넣었으나 모든 서버작업에 처리하고 싶으면, filter/UTF8Filter 클래스 추가

	```java
	@WebFilter(urlPatterns = {"/*"})
	@Log4j2
	public class UTF8Filter implements Filter {
		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			log.info("UTF8  filter....");
			HttpServletRequest req = (HttpServletRequest)request;
			req.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		}
	}
	```

### D. 리스너
1. 개념: 옵저버 패턴인 이벤트 리스너 사용
2. main/java/.../listener/WebAppListener 클래스 생성/작성 

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0038.png" width="600">

3. TodoListController.java doGet() 메서드에 리스너 확인코드 추가
4. 세션관련 리스너 LoginListener 클래스 추가/작성

[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP09.md)