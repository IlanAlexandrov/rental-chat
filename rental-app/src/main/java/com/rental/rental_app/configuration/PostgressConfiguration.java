package com.rental.rental_app.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


public class PostgressConfiguration {

	@Bean(name = "postgresDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.postgres")  // Bind to the custom prefix
	public DataSource postgresDataSource() {
		return DataSourceBuilder.create().build();
	}
}
