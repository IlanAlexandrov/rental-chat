package com.rental.rental_app.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfiguration {
	@Bean
	public Dotenv dotenv() {
		return Dotenv.load(); // Loads the .env file from the project root
	}
}
