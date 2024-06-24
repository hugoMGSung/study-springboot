// import logo from './logo.svg';
// import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import {Route, Routes } from 'react-router-dom';
import BoardList from './routes/BoardList';
import Home from './routes/Home';
import Login from './routes/Login';
import React from 'react';

function App() {
  return (
    <Routes>
      <Route path='/home' element={<Home/>} />
      <Route path='/board' element={<BoardList />} />
      <Route path='/qna' element={<BoardList />} />
      <Route path='/Login' element={<Login />} />
    </Routes>
  );
}

export default App;
