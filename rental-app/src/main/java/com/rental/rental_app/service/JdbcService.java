package com.rental.rental_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcService {
	private final JdbcTemplate jdbcTemplate;
	private final String COUNT_SQL = "SELECT COUNT(*) FROM vector_store";
	private final String DELETE_SQL = "DELETE FROM vector_store";
	private final JdbcClient jdbcClient;

	public int count() {
		return jdbcClient.sql(COUNT_SQL)
			.query(Integer.class)
			.single();
	}

	public void delete() {
		jdbcTemplate.update(DELETE_SQL);
	}
}
