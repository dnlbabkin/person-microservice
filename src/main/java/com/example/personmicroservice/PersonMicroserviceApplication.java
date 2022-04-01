package com.example.personmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PersonMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonMicroserviceApplication.class, args);
	}
}
