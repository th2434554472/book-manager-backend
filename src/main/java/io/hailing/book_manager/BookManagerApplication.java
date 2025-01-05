package io.hailing.book_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.hailing.book_manager.mapper")
public class BookManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagerApplication.class, args);
	}

}
