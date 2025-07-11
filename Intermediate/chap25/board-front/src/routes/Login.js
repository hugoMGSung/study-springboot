import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

import '../Nec.css';

function Login() {
  const navigate = useNavigate();

  const [user, setUser] = useState({
    username: '',
    password: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData();
      formData.append('username', user.username);
      formData.append('password', user.password);

      const response = await axios({
        url: 'http://localhost:8080/api/user',
        method: 'POST',
        data: formData,
        withCredentials: true,
      });
      if (response.status === 200) {
        alert('로그인 성공! ');
        console.log('유저아이디: ' + response.data.username);
        console.log('권한: ' + response.data.role);
        // console.log('Type'  + typeof(response.data));
        // localStorage로 변경
        localStorage.setItem("username", response.data.username);
        localStorage.setItem("password", response.data.password);
        localStorage.setItem("email", response.data.email);
        localStorage.setItem("role", response.data.role);
        localStorage.setItem("loginDt", new Date());
        console.log(response.data);
        console.log(localStorage);
        navigate('/home', { state: { userData: response.data } });
      }
    } catch (error) {
      console.log('로그인 에러: ', error);
    }
  };

  return (
  <div className="container">
    <div className='d-flex justify-content-center'>
      <div>
        <h3 className='text-center'>로그인</h3>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">사용자ID</label>
            <input type="text" name="username" placeholder="사용자ID" className='form-control'
                    value={user.username} onChange={handleChange} />
          </div>
          <div className='mb-3'>
            <label className='form-label'>비밀번호</label>
            <input type="password" name="password" placeholder="비밀번호" className='form-control'
                    value={user.password} onChange={handleChange} />
          </div>
          <button type="submit" className="btn btn-primary">로그인</button>
        </form>
      </div>
    </div>
  </div>
  );
}

export default Login;