package com.hugo83.listener;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Log4j2
public class WebAppListener implements ServletContextListener { // 웹서버 동작시 자동처리하고 싶은 작업
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("----------init---------------------------");
        log.info("----------init---------------------------");
        log.info("----------init---------------------------");

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("appName", "chap08");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("----------destroy---------------------------");
        log.info("----------destroy---------------------------");
        log.info("----------destroy---------------------------");
    }
}