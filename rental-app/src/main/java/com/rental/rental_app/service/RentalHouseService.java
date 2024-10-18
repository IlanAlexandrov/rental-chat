package com.rental.rental_app.service;

import com.rental.rental_app.entity.RentalHouse;
import com.rental.rental_app.repository.RentalHouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalHouseService {
	private final RentalHouseRepository rentalHouseRepository;

	public List<RentalHouse> getAllHouses() {
		return rentalHouseRepository.findAll();
	}

	public void saveHouses(List<RentalHouse> rentalHouses) {
		rentalHouseRepository.saveAll(rentalHouses);
	}

	public int countHouses() {
		return (int) rentalHouseRepository.count();
	}
}
