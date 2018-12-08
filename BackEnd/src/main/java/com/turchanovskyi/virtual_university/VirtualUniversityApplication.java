package com.turchanovskyi.virtual_university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class VirtualUniversityApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualUniversityApplication.class, args);
	}
}
