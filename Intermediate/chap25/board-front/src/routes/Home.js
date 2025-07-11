import React, { Component, useState } from 'react'
import { useLocation } from 'react-router-dom';
import axios from 'axios';

function Home() {
	const location = useLocation();
	var username = "";
	var role = "";

	if (localStorage != null) {
		username = localStorage.getItem("username");
		role = localStorage.getItem("role");
	}	

	return (
		<div className="container">
	  		<div>
				<h1>사용자 정보</h1>
				<p>이메일: {username}</p>
				<p>권한: {role}</p>
			</div>
		</div>
	)
}

export default Home;