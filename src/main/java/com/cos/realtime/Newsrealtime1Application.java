package com.cos.realtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Newsrealtime1Application {

	public static void main(String[] args) {
		SpringApplication.run(Newsrealtime1Application.class, args);
	}

}
