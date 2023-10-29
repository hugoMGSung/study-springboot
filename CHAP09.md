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
21. EventSampleDAOImpl 작성
22. 테스트 후
23. EventSampleDAOImpl에 @Primary 어노테이션 추가
24. src/java/패키지/lombok.config 작성
	```tex
	lombok.copyableAnnotations += org.springframework.beans.factory.annotation.Qualifier
	```

25. SampleService 클래스
	```java
	// 최종
	@Service
	@ToString
	@RequiredArgsConstructor
	public class SampleService {
		@Qualifier("event")
		private final SampleDAO sampleDAO;
	}

	```
26. EventSampleDAOImpl 클래스
	```java
	@Repository
	@Qualifier("event")
	public class EventSampleDAOImpl implements SampleDAO {
	}

	```

### B. 스프링 준비
1. build.gradle 에 Spring 라이브러리 추가
2. web.xml 리스너, context-param 추가
3. build.gradle MariaDB, HikariCP 라이브러리 추가
4. root-context.xml에 HikraiCP 관련 설정
	```xml
    <bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig">
        <property name="driverClassName" value="org.mariadb.jdbc.Driver"></property>
        <property name="jdbcUrl" value="jdbc:mariadb://localhost:3326/webdb"></property>
        <property name="username" value="webuser"></property>
        <property name="password" value="webuser"></property>
        <property name="dataSourceProperties">
            <props>
                <prop key="cachePrepStmts">true</prop>
                <prop key="prepStmtCacheSize">250</prop>
                <prop key="prepStmtCacheSqlLimit">2048</prop>
            </props>
        </property>
    </bean>

    <bean id="dataSource" class="com.zaxxer.hikari.HikariDataSource"
          destroy-method="close">
        <constructor-arg ref="hikariConfig" />
    </bean>	
	```

5. SampleTests로 테스트 메서드 작성 후 실행

### C. MyBatis 연동
1. build.gradle MyBatis 라이브러리추가
2. root-context.xml에 Mybatis용 SqlSessionFactory 설정

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0047.png" width="600">

3. resources/mappers 폴더 생성. 위의 빨간색 오류에서 mappers가 사라지는 것을 확인
4. main/java/패키지/mapper/TimeMapper 인터페이스 작성
5. root-context.xml에 MyBatis 설정 추가
	```xml
	<mybatis:scan base-package="com.hugo83.chap09.mapper"></mybatis:scan>
	```
6. 패키지명/mapper/TimeMapperTests 테스트코드 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0048.png" width="600">

7. MyBatis XML로 SQL 분리, TimeMapper2 인터페이스 작성
8. resources/mapper/TimeMapper2.xml 작성
	```xml
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper
			PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
			"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.hugo83.chap09.mapper.TimeMapper2">
		<select id="getNow" resultType="string">
			select now()
		</select>
	</mapper>
	```

9. TimeMapperTests에 두번째 메서드 테스트

### D. Spring MVC 연동
1. WEB-INF/servlet-context.xml 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0049.png" width="600">

2. 위의 붉은 색 부분, webapp/resources 폴더 생성
3. web.xml에 DispatcherServlet 설정
	```xml
    <servlet>
        <servlet-name>appServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/servlet-context.xml</param-value>
        </init-param>

        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>appServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    <!-- 2차 -->	
	```

4. servlet-context.xml 에 <context:component-scan 추가
5. controller/SampleController.java /hello 처리 메서드 작성
6. WEB-INF/views/hello.jsp 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0050.png" width="600">

### E. Todo 관련 작업 시작!
1. controller/TodoController 클래스 작성
	```java
	@Controller
	@RequestMapping("/todo")
	@Log4j2
	public class TodoController {
		@RequestMapping("/list")
		public void list() {
			log.info("todo list.......");
		}

		@RequestMapping(value = "/register", method = RequestMethod.GET)
		public void register() {
			log.info("todo register........");
		}
	}
	```

2. 페이지가 없어서 404 에러발생
3. TodoController의 소스 수정
	```java
	//@RequestMapping(value = "/register", method = RequestMethod.GET)
    @GetMapping(value = "/register")
    public void registerGET() {
        log.info("GET Todo register........");
    }

    @PostMapping(value = "/register")
    public void registerPOST() {
        log.info("POST Todo register........");
    }
	```

4. SampleController 클래스 여러개 테스트
5. controller/formater/LocalDateFormatter.java 클래스 작성 (Jakarta EE 에서는 오류나지 않음)
6. servlet-context.xml 추가 수정
	```xml
    <bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
        <property name="formatters">
            <set>
                <bean class="com.hugo83.chap09.controller.formatter.LocalDateFormatter"/>
            </set>
        </property>
    </bean>

	<mvc:annotation-driven  conversion-service="conversionService" />
	```

7. dto/TodoDTO 클래스 작성
8. TodoController POST 메서드 수정
9. WEB-INF/views/todo/register.jsp 작성(BootStrap 적용) / 한글 깨짐

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0051.png" width="600">

10. web.xml encoding 태그 추가
	```xml
	<filter>
        <filter-name>encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>

    <filter-mapping>
        <filter-name>encoding</filter-name>
        <servlet-name>appServlet</servlet-name>
    </filter-mapping>
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0052.png" width="600">

### F. 예외처리
1. controller/exception/CommonExceptionAdvice 클래스 작성
2. WEB-INF/views/custom404.jsp 작성
3. web.xml에 예외처리 부분 추가
3. localhost:8080/aaa/bbb 로 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0053.png" width="600">

### G. Todo 웹사이트 개발
1. build.gradle에 DTO/VO간 변환, DTO 검증용 라이브러리 추가
2. Sample 관련 내용 삭제 권장
3. MariaDB tbl_todo 테이블 삭제후 재생성
	```sql
	DROP TABLE tbl_todo;

	CREATE TABLE tbl_todo (
		tno INT AUTO_INCREMENT PRIMARY KEY,
		title VARCHAR(100) NOT NULL,
		dueDate DATE NOT NULL,
		writer VARCHAR(50) NOT NULL,
		finished TINYINT DEFAULT 0
	);
	```

4. config/ModelMapperConfig.java 작성
5. root-context.xml 에 config 패키지를 component-scan에 추가
	```xml
	<context:component-scan base-package="com.hugo83.chap09.config"/>
    <context:component-scan base-package="com.hugo83.chap09.service"/>
	```
6. Bootstrap 내용
	1. container, row, col 클래스 관련 적용
	2. card 컴포넌트 클래스 사용
	3. navbar 클래스
	4. footer 클래스 등...

7. Mybatis 개발순서
	1. VO선언
	2. XML 개발
	3. Mapper 인터페이스 개발
	4. 테스트코드 작성

8. domain/TodoVO 작성
9. mapper/TodoMapper 인터페이스 작성, getTime() 하나만...
10. resources/mappers/TodoMapper.xml 작성
11. test/.../mapper/TodoMapperTests 작성 후 테스트
12. log4j2.xml 에 일반로그 및 SQL 로그까지 설정
	```xml
	<configuration status="INFO">
		<Appenders>
			<!-- 콜솔 -->
			<Console name="console" target="SYSTEM_OUT">
				<PatternLayout charset="UTF-8" pattern="%d{hh:mm:ss} %5p [%c] %m%n"/>
			</Console>
		</Appenders>

		<loggers>
			<logger name="org.springframework" level="INFO" additivity="false">
				<appender-ref ref="console" />
			</logger>
			<logger name="com.hugo83" level="INFO" additivity="false">
				<appender-ref ref="console" />
			</logger>
			<logger name="com.hugo83.chap09.mapper" level="TRACE" additivity="false">
				<appender-ref ref="console" />
			</logger>
			<root level="INFO" additivity="false">
				<AppenderRef ref="console"/>
			</root>
		</loggers>
	</configuration>
	```

13. TodoMapper 인터페이스에 insert() 메서드 추가
14. TodoMapper.xml 에 insert() 에 대한 쿼리 작성
15. TodoMapperTests에 testInsert() 작성 후 테스트, DB 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0054.png" width="500">

16. 패키지/controller/dto 폴더를 패키지/dto로 이전
17. Mapper와 Controller 사이의 서비스계층 처리, service/TodoService 인터페이스 내 register() 작성
18. service/TodoServiceImpl.java 클래스 작성
19. root-context.xml 확인할 것
20. test/.../service/TodoServiceTests 클래스 작성, 테스트

21. TodoController에서 GET/POST 처리. 데이터 로그 후 리다이렉션까지 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0055.png" width="600">

22. TodoDTO hibernate로 입력검증 추가
23. TodoController에서 POST 처리시 검증반영 추가
	타이틀, 글쓴이가 비었거나 마감일자가 오늘 이전이면 화면이 재실행됨

24. register.jsp에서 검증 에러메시지 확인 스크립트 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0056.png" width="650">

25. TodoController 에서 등록기능 완성, registerPOST() 에서 TodoService 기능 호출
26. 테스트 시 등록 후 localhost:8080/todo/list로 이전, list.jsp 없음

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0058.png" width="600">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0059.png" width="500">

27. mapper/TodoMapper에 selectAll() 추가
28. mappers/TodoMapper.xml에 selectAll id 추가
29. test/.../mapper/TodoMapperTests에 testSelectAll() 작성 후 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0060.png" width="600">

30. TodoService 인터페이스에 getAll() 작성
31. TodoServiceImpl 클래스에 getAll() 구현
32. TodoController의 list() 에 Model 추가 후 jsp에 전달하는 기능 추가
33. views/list.jsp 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0061.png" width="600">

34. TodoMapper 인터페이스에 selectOne() 추가
35. mappers/TodoMapper.xml에 selectOne 쿼리 추가
36. testSelectOne() 테스트 메서드 추가, 확인
37. TodoService 인터페이스에 getOne(Long tno) 추가, TodoServiceImple에 구현
38. TodoController 에 read() 메서드 작성
39. views/todo/read.jsp 작성
40. views/todo/list.jsp 링크 수정
	```html
	<td>
	<a href="/todo/read?tno=${dto.tno}" class="text-decoration-none" data-tno="${dto.tno}" >
	<c:out value="${dto.title}"/>
	</a>
	</td>
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0062.png" width="600">


### H. 수정 및 삭제 기능 추가
1. TodoController의 read()에 /modify GetMapping 추가
2. read.jsp를 복사 modify.jsp 구성
3. TodoController 에 remove() 기본 구현(아직 저장처리 없음)
4. TodoMapper 인터페이스와 TodoMapper.xml 에 delete() 작성
5. TodoService, TodoServiceImpl 에 remove() 작성
6. TodoController remove() 수정
7. 삭제 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0063.png" width="600">