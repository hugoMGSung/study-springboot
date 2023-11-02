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
	
2. dto/ReplyDTO.java 작성







[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP12.md)