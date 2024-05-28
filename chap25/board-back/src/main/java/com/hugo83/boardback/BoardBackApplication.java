package com.hugo83.boardback;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hugo83.boardback.model.Board;
import com.hugo83.boardback.repository.BoardRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class BoardBackApplication implements CommandLineRunner {

	@Autowired
	private BoardRepository boardRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BoardBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		IntStream.rangeClosed(1, 50).forEach(i -> {
			Board board = Board.builder()
					.title("타이틀..." + i)
					.content("내용 컨텐츠..." + i)
					.writer("작성자" + (i % 10))
					.regDate(LocalDateTime.now())
					.build();

			Board result = boardRepository.save(board);
			log.info("BNO:" + result.getBno());
		});
	}

}
