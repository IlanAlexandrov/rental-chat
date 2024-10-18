package com.rental.rental_app.service;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DataLoaderService {

	private final RentalHouseService rentalHouseService;
	private final RentalHouseGeneratorService rentalHouseGeneratorService;

	@PostConstruct
	public void load() throws IOException {
		int count = rentalHouseService.countHouses();
		if (count == 0) {
			rentalHouseGeneratorService.loadRentalHousesFromFile();
		}
	}
}
