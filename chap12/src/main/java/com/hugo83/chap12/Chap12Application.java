package com.hugo83.chap12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Chap12Application {

	public static void main(String[] args) {
		SpringApplication.run(Chap12Application.class, args);
	}

}
