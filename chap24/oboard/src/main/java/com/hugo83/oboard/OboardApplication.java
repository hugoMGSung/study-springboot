package com.hugo83.oboard;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hugo83.oboard.entity.Board;
import com.hugo83.oboard.repository.BoardRepository;

@SpringBootApplication
public class OboardApplication implements CommandLineRunner {

	@Autowired
	private BoardRepository boardRepository;

	public static void main(String[] args) {
		SpringApplication.run(OboardApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Board b1 = Board.builder().title("첫번째 게시물입니다.").content("첫번째 게시물입니다. 감사합니다.")
				.writer("휴고성").regDate(LocalDateTime.now()).build();
		Board b2 = Board.builder().title("두번째 게시물입니다.").content("두번째 게시물입니다. 안녕히가세요.")
				.writer("애슐리").regDate(LocalDateTime.now()).build();

		List<Board> boardList = List.of(b1, b2);
		boardRepository.saveAll(boardList);
	}
}
