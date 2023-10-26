# study-springboot
스프링부트 저장소 다시 만들기

## chap09. Spring Web MVC
스프링부트 = 스프링 + 추가기능

1. 서버가 내장되어 별도 WAS 설치 필요없음
2. Starter 기능으로 라이브러리 의존성 관리 편리
3. 스프링에 자주 사용하던 설정을 어노테이션으로 자동등록
4. 내장된 서버가 있어 jar로 배포 단독실행 가능

### A. 프로젝트 생성
0. Jakarta EE 선택
1. com.hugo83.chap09 로 지정(이전과 동일)
2. 템플릿: 웹 애플리케이션 선택
3. 애플리케이션 서버: Tomcat 9.0.82 선택
4. 언어: Java
5. 시스템 빌드: Gradle
6. JDK: 17
7. 버전: Java EE 8 선택 -> 생성 클릭
8. 실행/디버그 구성, VM 옵션: -Dfile.encoding=UTF-8 설정
9. 스프링 라이브러리 추가
	```tex
	implementation group: 'org.springframework', name: 'spring-core', version: '5.3.19'
    implementation group: 'org.springframework', name: 'spring-context', version: '5.3.19'
    implementation group: 'org.springframework', name: 'spring-test', version: '5.3.19'

	compileOnly 'org.projectlombok:lombok:1.18.24'
    annotationProcessor 'org.projectlombok:lombok:1.18.24'
    testCompileOnly 'org.projectlombok:lombok:1.18.24'
    testAnnotationProcessor 'org.projectlombok:lombok:1.18.24'

	implementation group: 'org.apache.logging.log4j', name: 'log4j-core', version: '2.17.2'
    implementation group: 'org.apache.logging.log4j', name: 'log4j-api', version: '2.17.2'
    implementation group: 'org.apache.logging.log4j', name: 'log4j-slf4j-impl', version: '2.17.2'

	implementation group: 'jstl', name: 'jstl', version: '1.2'
	```
10. resources/log4j2.xml 작성
11. 아래 sample/SampleDAO, SampleService 빈 클래스 추가
12. webapp/WEB-INF/에 새로만들기>XML 구성파일>Spring 구성>root-context.xml 생성,작성
	```xml
	<bean class="com.hugo83.chap09.sample.SampleDAO"></bean>
    <bean class="com.hugo83.chap09.sample.SampleService"></bean>
	```
13. test/com.hugo83.chap09.sample 패키지 추가
14. SampleTests 클래스 작성
15. SampleService 작성, @Autowired 오류확인
	```java
	@ToString
	public class SampleService {
		@Autowired
		private SampleDAO sampleDAO;
	}
	```
16. root-context.xml 재변경 
	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:context="http://www.springframework.org/schema/context"
		xsi:schemaLocation="http://www.springframework.org/schema/beans 
							http://www.springframework.org/schema/beans/spring-beans.xsd
							http://www.springframework.org/schema/context 
							https://www.springframework.org/schema/context/spring-context.xsd">

		<context:component-scan base-package="com.hugo83.chap09.sample" />
	</beans>
	```

17. SampleDAO 클래스 작성
18. SampleService에 @Service 어노테이션 추가
	```java 
	// 최종
	@Service
	@ToString
	@RequiredArgsConstructor
	public class SampleService {
		private final SampleDAO sampleDAO;
	}
	```
19. SampleDAO를 인터페이스로 변경
20. SampleDAOImpl 작성
21. 