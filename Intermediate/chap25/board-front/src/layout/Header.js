import React from "react";
import { Link, useNavigate } from "react-router-dom";

const Header = () => {

  const navigate = useNavigate();

  const gotoLogin = () => {
    navigate("/Login");
  }

  const username = localStorage.getItem("username");
  console.log(localStorage.getItem("username") == null );

  return (
    <div className="container">
      <header className="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <div className="col-md-1 mb-2 mb-md-0">
          <a href="/home" className="d-inline-flex link-body-emphasis text-decoration-none">
            <img src={require('../logo.png')} alt="logo" width={40} />
          </a>
        </div>

        <ul className="nav col-12 col-md-6 mb-2 justify-content-center mb-md-0">
          <li>
            <Link to="/home" className="nav-link px-2 link-secondary">
              홈
            </Link>
          </li>
          <li>
            <Link to="/board" className="nav-link px-2">
              게시판
            </Link>
          </li>
          <li>
            <Link to="/qna" className="nav-link px-2">
              질문응답
            </Link>
          </li>
          {/* <li>
            <a href="#" className="nav-link px-2">
              FAQs
            </a>
          </li>
          <li>
            <a href="#" className="nav-link px-2">
              About
            </a>
          </li> */}
        </ul>
        <div className="col-md-2 text-end">
          <div className="text-end">
              ID : {username}
          </div>
        </div>
        <div className="col-md-3 text-end">
        {localStorage.getItem("username") != null ? (<button type="button" className="btn btn-outline-primary me-2">
            로그아웃
          </button>): (<button type="button" className="btn btn-outline-primary me-2" onClick={gotoLogin}>
            로그인
          </button>)}
        {localStorage.getItem("username") != null ?? (<button type="button" className="btn btn-primary">
            회원가입
          </button>)}
        </div>
      </header>
    </div>
  );
};

export default Header;
