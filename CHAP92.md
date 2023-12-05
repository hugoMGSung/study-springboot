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

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0301.png" width="600">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0302.png" width="600">

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

### C. 연동 테스트
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

4. 리액트 서버와 스프링부트 실행 후 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0305.png" width="600">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0306.png" width="600">