package com.hugo83.chap11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Chap11Application {

	public static void main(String[] args) {
		SpringApplication.run(Chap11Application.class, args);
	}

}
