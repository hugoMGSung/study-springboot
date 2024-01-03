# study-springboot
스프링부트 저장소 다시 만들기

## chap91. SpringBoot ToyProject
배운 것 모아 토이프로젝트2 - Netflix 아니 Nitflex

### A. VS Code Spring Boot 설정
1. Spring Initializr: Create a Gradle Project...
2. Specify Spring Boot version: 3.1.6 (3.1.5는 어디갔냐?)
3. Specify project language: Java
4. Input Group Id: com.hugo83
5. Input Artifact Id: nitflex
6. Specify packaging type: Jar
7. Specify Java version: 17
8. Choose dependencies: Last Used 선택(나중에 바꿀거니깐...)
9. HeigiSQL에서 nitflex 데이터베이스 생성
10. 사용자 nitflexuser / nitflexp@ssw0rd 로 생성
11. application.properties 에 DB 설정 먼저 추가하기
12. 실행여부 확인 - Whitelabel Error Page 확인
13. Swagger 라이브러리 추가
14. RestAPI 설정 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0301.png" width="700">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0302.png" width="700">

### B. Spring Boot API 구현
1. build.gradle dependencies 작성
2. application.properties 콘솔로그, Log4J2 설정, Maria DB 설정 작성
3. /config/MyBatisConfig.java 작성
4. /domain/ResponseVO.java 작성
5. /mapper/UserMapper.java 작성
	```java
	@Mapper
	@Repository
	public interface UserMapper {
		ArrayList<HashMap<String, Object>> findAll();
	}
	```
6. /resources/mapper/UserMapper.xml 작성
	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.hugo83.nitflex.mapper.UserMapper">

		<select id="findAll" resultType="HashMap"> 
			select * from nf_user
		</select>
	</mapper>
	```

7. /service/UserService.java 작성
8. /controller/UserController.java 작성
	```java
	@RestController
	@RequestMapping(value = "/api/v1/user/")
	@Log4j2
	public class UserController {
		@Autowired
		UserService userService;

		@RequestMapping(value = "findAll", method = RequestMethod.POST)
		public ResponseEntity<?> findAll() {
			ResponseVO responseVO = new ResponseVO();
			responseVO.setResultCode("S0001"); // 역시 결과를 보고 만들면 됨.
			log.info("resultCode는 결과 리턴 후 나오는 값에 따라 조절할 것!");
			responseVO.setRes(userService.findAll());
			return new ResponseEntity<>(responseVO, HttpStatus.OK);
		}
	```
9. 서버 실행 후 swagger-ui 실행

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0314.png" width="700">

10. /domain/UserVO.java 작성
	```java
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class UserVO {
		private Long idx;
		private String email;
		private String password;
		private String name;
		private LocalDate red_date;
		private LocalDate mod_date;
	}
	```

11. /mapper/UserMapper.java 나머지 작성
12. /reources/mapper/UserMapper.xml 나머지 작성
13. /service/UserService.java 나머지 작성
14. /controller/UserController.java 나머지 작성
15. /domain/FavoriteMoviesVO.java 작성
16. /mapper/FavoriteMoviesMapper.java 작성
17. /service/FavoriteMoviesService.java 작성
18. /config/CORSFilter.java 작성
19. /controller/FavoriteMoviesController.java 작성

20. /domain/CommentVO.java 작성
21. /mapper/CommentMapper.java 작성
22. /service/CommentService.java 작성
23. /controller/CommentController.java 작성

24. /resource/application-API-KEY.properties 생성 TMDB api-key 입력
25. /resource/application.properties 에 위의 키를 include
26. /service/TmdbMovieService.java 작성
27. /controller/TmdbMovieController.java 작성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0315.png" width="700">


### B. React 설치
1. 명령 프롬프트 실행
2. nvm -v 로 확인. 현재 버전이 너무 낮아서 다시 재설치
3. NVM for Windows 설치. https://github.com/coreybutler/nvm-windows 활용
4. 환경변수
	- NVM_HOME: C:\DEV\TOOLS\nvm
	- NVM_SYMLINK: C:\DEV\LANGS\nodejs 

5. nvm 명령으로 node.js 설치
	```shell
	> nvm install 18
	> nvm list
	> nvm use 18.19.0
	> node --version
	```
6. npm, npx 등의 명령을 수행할 수 있음
7. Spring Boot 루트 폴더에 frontend 폴더 생성
8. Spring Boot 루트 콘솔 오픈 후 명령 실행
	```shell
	> npx create-react-app frontend
	```
9. Happy hacking! 이 보이면 성공
10. 콘솔에서 frontend로 이동, npm 명령 실행
	```shell
	> npm start
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0304.png" width="600">

### C. React / Spring Boot 통합테스트
1. /frontend/package.json 수정
	```json
	"scripts": {
		"start": "react-scripts start",
		"build": "react-scripts build",
		"test": "react-scripts test",
		"eject": "react-scripts eject"
	},
	"proxy": "http://localhost:8080", // 추가
	```
2. /controller/HomeController.java /test GetMapping 추가
3. /frontend/src/App.js 수정
	```javascript
	import React, { useState, useEffect } from 'react';
	import logo from './logo.svg';
	import './App.css';

	function App() {
	const [message, setMessage] = useState("");

	useEffect(() => {
		fetch('/test')
		.then(response => response.text())
		.then(message => {
			setMessage(message);
		});
	}, []);

	return (
		<div className="App">
		<header className="App-header">
			<img src={logo} className="App-logo" alt="logo" />
			<h1 className='App-title'>{ message}</h1>
			<a
			className="App-link"
			href="https://reactjs.org"
			target="_blank"
			rel="noopener noreferrer"
			>
			Learn React
			</a>
		</header>
		</div>
	);
	}

	export default App;
	```

4. 중요! 명령 프롬프트의 리액트 서버 실행 종료
5. build.gradle 에 플러그인 추가(이것도 옵션)
	```groovy
	plugins {
		//...
		id 'com.moowork.node' version '1.3.1'
	}
	```

6. build.gradle의 맨 아래에 아래의 코드 추가
	```groovy
	def reactAppDir = "$projectDir/frontend"	//리액트경로

	sourceSets {
		main {
			resources {
				srcDirs = ["$projectDir/build", "$projectDir/src/main/resources"]
			}
		}
	}

	processResources {
		dependsOn "copyReactFile"
	}

	task copyReactFile(type: Copy){	
	//build 된 React 정적파일을 Spring Boot 정적파일 디렉토리로 이동
		dependsOn "buildReact"
		from "$reactAppDir/build"
		into "$projectDir/src/main/resources/static"
	}

	task buildReact(type: Exec) {	//React build 
		dependsOn "installReact"
		workingDir "$reactAppDir"
		inputs.dir "$reactAppDir"
		group = BasePlugin.BUILD_GROUP
		if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
			commandLine "npm.cmd","run-script","build"
		} else {
			commandLine "npm","run-script","build"
		}
	}

	task installReact(type: Exec) {	 //프로젝트에 필요한 라이브러리 install
		dependsOn "deleteReactFile"
		workingDir "$reactAppDir"
		inputs.dir "$reactAppDir"
		group = BasePlugin.BUILD_GROUP
		if(System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')){
			commandLine "npm.cmd","audit","fix"
			commandLine "npm.cmd","install"
		}else{
			commandLine "npm","audit","fix"
			commandLine "npm","install"
		}
	}

	task deleteReactFile(type: Delete) {	 //static 폴더 삭제
		delete "$projectDir/src/main/resources/static"
	}
	```

7. VSCode gradle 탭에서 nitflex > build > clean 실행
8. gradle 탭에서 nitflex > other > processResources 실행. 이때 /src/main/resources/static 아래 삭제되고 다시 생성되는 것 확인할 것

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0307.png" width="400">

9. Spring Boot 서버 실행시 build.gradle의 리액트 설정은 주석처리(중요!!!) 뒤 실행

	3000 이 아닌 8080 으로 React 실행되는 것 확인할 것!

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0308.png" width="600">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0309.png" width="600">

10. 개발시에는 react는 3000번으로 따로 개발툴을 사용, Spring Boot는 8080 등으로 활용뒤 마지막에 서버 합칠때만 사용할 것


### D. DB 생성 및 엔티티 작업
1. HeidiSQL에서 테이블 생성

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0310.png" width="600">

2. /entity/User.java 생성 --> 생략할래...

### E. react 작업
1. /frontend/public/nilflex_logo.png 저장
2. /frontend/public/index.html 변경

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0316.png" width="700">

3. /src/ 내
	- apis
	- components
	- routes
	- styles 

	로 폴더 추가

4. /src/styles/App.css, Detail.css, Login.css, index.css 작업처리
5. /src/components/App.js 옮기기
6. /src/index.js 수정
7. /src/components/App.js 수정

	```javascript
	import React, { Component } from "react";
	import { Container } from "reactstrap"; // reactstrap 이 없음

	import '../styles/App.css';

	class App extends Component {
	render() {
		return (
		<div className="App">
			{/* <MainNavbar /> */}
			<Container className="classname">
			{/* <Router /> */}
			</Container>
		</div>
		);
	}
	}

	export default App;
	```

8. 터미널에서 reactstrap, bootstarp 을 추가. 부트스트랩은 reactstrap에 없으므로 같이 추가
 
	- ~~npm install --save reactstrap 옛날방식~~
	- ~~npm install --save bootstarp ~~

	```shell
	> npm i bootstrap
	> npm i reactstrap react react-dom 
	```
9. 문제 발생시 아래 명령어 실행

	```shell
	> npm audit fix --force 
	```

10. 위의 App.js 에서 <MainNavbar /> 부분 처리를 위해 /routes/MainNavbar.js 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0317.png" width="700">

11. MainNavbar.js 작성
12. 콘솔에 라이브러리 추가

	```shell
	> npm i react-bootstrap-icons
	```
13. /components/SearchBar.js 작성 후 MainNavbar.js에 추가

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0318.png" width="700">