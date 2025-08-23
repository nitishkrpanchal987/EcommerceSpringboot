package com.practice.ecommercePrac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class EcommercePracApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercePracApplication.class, args);
	}

}
