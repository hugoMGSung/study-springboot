# study-springboot
스프링부트 저장소 다시 만들기

## chap11. Spring REST
AJAX, JSON
REST방식 서비스

1. GET: /replies/list/:bno
		/replies/:rno
2. POST: /replies
3. PUT: /replies/:rno
4. DELETE: /replies/:rno

### A. REST 프로젝트
1. 프로젝트 생성 후 Swagger 설정. 3.x 이후는 아래와 같이..
	```tex
	implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0087.png" width="600">

2. 필요없는 것들(Spring Boot 3 이상에서는 필요없음)
	- config/SwaggerConfig
	- config/CustomServletConfig

3. dto/ReplyDTO.java 작성
4. controller/ReplyController 클래스 작성

	@ApiOperaion 불필요

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0088.png" width="600">

5. REST방식 에러 출력을 위해 @RestControllerAdvice 설계. controller/advice/CustomRestAdvice 클래스 추가
6. dto/ReplayDTO 편집
7. ReplyController register() 메서드 수정 
8. Swagger 화면에서 replies() 메서트 테스트, Try it 누른뒤 Request Body를 { } 로 변경뒤 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0089.png" width="600">

9. 오류 발생. 값을 제대로 넣어도 안들어감 - 차후 확인요

### B. 다대일 관계 구현
1. domain/Reply 클래스 작성
2. repository/ReplyRepository 인터페이스 작성
3. test/.../repository/ReplyRepositoryTests에 testInsert() 작성, 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0090.png" width="500">

4. dto/BoardListReplyCountDTO.java 작성
5. repository/search/BoardSearch 인터페이스에 댓글용 메서드 추가
6. 이에 대한 BoardSearchImpl.java 에서 위의 메서드 구현
7. test/.../repository/BoardRepositoryTests 의 testSearchReplyCount() 작성

### C. 게시물 목록 화면 처리
1. service/BoardService 인터페이스에 메서드 추가
2. service/BoardServiceImpl 클래스에 위의 listWithReplyCount() 작성
3. controller/BoardController의 list() 내 서비스의 list()를 listWithReplyCount()로 변경
4. list.html에 replyCount 속성 출력하게 변경

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0091.png" width="600">

5. service/ReplyService 인터페이스 작성
6. service/ReplyServiceImpl 클래스 작성
7. test/.../service/ReplyServiceTests 클래스 작성, 테스트 (bno 등록오류)
	
	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0092.png" width="600">

8. ReplyController에 ReplyServie 주입

일단 PASS!!! 다시!!!


[Next](./CHAP13.md)