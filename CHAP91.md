# study-springboot
스프링부트 저장소 다시 만들기

## chap91. SpringBoot ToyProject
배운 것 모아 토이프로젝트 1 - MyBatis 구현

### A. VSCode 기본 설정
1. VSCode에서 기본 SpringBoot Gradle Initializr 진행
2. spring-boot-starter-web 이 없으면 톰캣서버 실행 안됨
3. 기본적으로 spring-boot-devtools, spring-boot-starter-web 은 선택할 것

4. 최종 구조

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0311.png" width="300">

5. build.gradle 작성
	```groovy
	dependencies {
		// 최초 프로젝트 설정 기본
		implementation 'org.springframework.boot:spring-boot-starter-web'
		implementation 'org.springframework.boot:spring-boot-starter'
		developmentOnly 'org.springframework.boot:spring-boot-devtools'
		testImplementation 'org.springframework.boot:spring-boot-starter-test'
		// Spring Boot 추가 라이브러리
		implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.0'
		// implementation 'org.springframework.boot:spring-boot-starter-cache'
		// Lombok 기본/ 테스트
		compileOnly 'org.projectlombok:lombok'
		annotationProcessor 'org.projectlombok:lombok'
		testCompileOnly 'org.projectlombok:lombok'
		testAnnotationProcessor 'org.projectlombok:lombok'
		// ModelMapper 라이브러리
		implementation 'org.modelmapper:modelmapper:3.1.0'
		// REST용 라이브러리
		implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
		// MariaDB 라이브러리
		runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
	}
	```

6. DB 구성

	```sql
	-- toybatis 데이터베이스 구조 내보내기
	CREATE DATABASE IF NOT EXISTS `toybatis` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
	USE `toybatis`;

	-- 프로시저 toybatis.loopInsert 구조 내보내기
	DELIMITER //
	CREATE PROCEDURE `loopInsert`()
	BEGIN
		DECLARE i INT DEFAULT 1;
			
		WHILE i <= 50 DO
		INSERT INTO tb_user (email , password, reg_date , mod_date)
		VALUES (concat('user_',i), concat('pass_',i), NOW(), now());
		SET i = i + 1;
	END WHILE;
	END//
	DELIMITER ;

	-- 테이블 toybatis.tb_user 구조 내보내기
	CREATE TABLE IF NOT EXISTS `tb_user` (
	`Idx` bigint(20) NOT NULL AUTO_INCREMENT,
	`email` varchar(120) NOT NULL DEFAULT '0',
	`password` varchar(120) NOT NULL DEFAULT '0',
	`reg_date` date DEFAULT NULL,
	`mod_date` date DEFAULT NULL,
	PRIMARY KEY (`Idx`)
	) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='사용자 유저 테이블(테스트용)';
	```

6. application.properties 작성
	```python
	## 콘솔로그 색상
	spring.output.ansi.enabled=always
	## Log4J 설정
	logging.level.org.springframework=info
	logging.level.com.hugo83=debug
	logging.level.org.springframework.security=trace
	## DB 설정
	spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
	spring.datasource.jdbc-url=jdbc:mariadb://localhost:3326/toybatis  ## 여기 중요!
	spring.datasource.username=toybatis
	spring.datasource.password=toybatis
	spring.datasource.mapper-locations=classpath:/mapper/**/*.xml  ## 이거 중요!
	# Mapper Xml Location
	# mybatis.mapper-locations=classpath:mappers/*.xml
	mybatis.configuration.map-underscore-to-camel-case=true
	## SWAGGER
	spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER
	```

### B. CRUD 구현 진행
1. /dto/ResponseDTO.java 작성
	```java
	@Data
	@NoArgsConstructor
	public class ResponseDTO {
		private String resultCode;
		private Object res;
	}
	```
2. /mapper/UserMapper.java 작성
	```java
	@Mapper
	@Repository
	public interface UserMapper {
		ArrayList<HashMap<String, Object>> findAll();
	```
3. /resources/mapper/UserMapper.xml 작성
	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.hugo83.toybatis.mapper.UserMapper">
		<!-- <cache /> -->

		<select id="findAll" resultType="HashMap"> 
			select * from tb_user 
		</select>

	```
4. /service/UserService.java 작성
	```java
	@Service
	public class UserService {
		@Autowired
		UserMapper userMapper;

		public ArrayList<HashMap<String, Object>> findAll() {
			return userMapper.findAll();
		}
	```
5. /controller/UserController.java 작성
	```java
	@RestController
	@RequestMapping(value = "/api/user")
	public class UserController {

		@Autowired
		UserService userService;

		@GetMapping(value = "/findAll")
		public ResponseEntity<?> findAll() {
			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setResultCode("S0001"); // 성공 실패에 따라 나오도록 해주면 됨
			responseDTO.setRes(userService.findAll());
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		}
	```
6. Swagger-ui 실행 후 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0312.png" width="600">
	
2. /domain/User.java 도메인 클래스 작성
