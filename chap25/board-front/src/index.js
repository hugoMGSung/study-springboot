import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import { BrowserRouter } from "react-router-dom";
// import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  // <React.StrictMode>
  <div className="App h-full w-full">
    <BrowserRouter>
      <div id="wrapper" className="flex flex-col h-screen">

        <Header />
        <div id="main-content" className="flex-1">
          <App />
        </div>
        <Footer />
      </div>
    </BrowserRouter>
  </div>
  // </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
