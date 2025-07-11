package com.hugo83.chap10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Chap10Application {

	public static void main(String[] args) {
		SpringApplication.run(Chap10Application.class, args);
	}

}
