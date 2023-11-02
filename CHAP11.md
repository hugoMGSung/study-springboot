# study-springboot
스프링부트 저장소 다시 만들기

## chap11. Spring Web MVC
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







[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP12.md)