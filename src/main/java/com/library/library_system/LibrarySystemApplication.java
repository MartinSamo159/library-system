package com.library.library_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

//Hlavní třída na spuštění aplikace přes Spring Boot

@SpringBootApplication
public class LibrarySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {
        };
    }
}
