package com.hugo83.chap02;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

	public void init() {
		System.out.println("init()...");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 4. 해당 url이 호출되면 실행할 비즈니스 로직 작성
		System.out.println("HelloServlet.service");
		System.out.println("request = " + req);
		System.out.println("response = " + resp);

		String username = req.getParameter("username");
		System.out.println("username = " + username);

		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write("hello " + username);
	}

	@Override
	public void destroy() {
		System.out.println("destroy()...");
	}

}
