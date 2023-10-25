package com.hug83.chap09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Chap09Application {

	public static void main(String[] args) {
		SpringApplication.run(Chap09Application.class, args);
	}

}
