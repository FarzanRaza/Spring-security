package com.example.blog1;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Blog1Application {

	public static void main(String[] args) {
		SpringApplication.run(Blog1Application.class, args);
	}
		@Bean
		ModelMapper model(){
		return new ModelMapper();
		}

}
