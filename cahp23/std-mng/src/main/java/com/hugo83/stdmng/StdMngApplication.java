package com.hugo83.stdmng;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hugo83.stdmng.entity.Student;
import com.hugo83.stdmng.repository.StudentRepository;

@SpringBootApplication
public class StdMngApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(StdMngApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Student s1=Student.builder().name("Sunio").address("Hong Kong").email("sunio345@gmail.com").build();
		Student s2=Student.builder().name("Gian").address("Hokaido").email("gian445@gmail.com").build();
		Student s3=Student.builder().name("성명건").address("한국").email("hugoMGSung@gmail.com").build();
		Student s4=Student.builder().name("Sizuka").address("Tokyo").email("sizuka3aa45@gmail.com").build();
		Student s5=Student.builder().name("Doraemon").address("Japan").email("doraemon345@gmail.com").build();
	
		List<Student> studentList=List.of(s1,s2,s3,s4,s5);
		studentRepository.saveAll(studentList);
	}

}
