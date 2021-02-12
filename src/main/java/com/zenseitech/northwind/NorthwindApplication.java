package com.zenseitech.northwind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class NorthwindApplication {

	// VM Options: -Dspring.profiles.active=sqlite
	public static void main(String[] args) {
		SpringApplication.run(NorthwindApplication.class, args);
	}

}
