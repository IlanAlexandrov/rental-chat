package com.rental.rental_app.service;


import com.rental.rental_app.entity.RentalHouse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataLoaderService {

	private final JdbcService jdbcService;
	private final RentalHouseGeneratorService rentalHouseGeneratorService;
	private final VectorStore vectorStore;

	@PostConstruct
	public void load() throws IOException {
		jdbcService.delete();
		int count = jdbcService.count();
		if (count == 0) {
			List<RentalHouse> rentalHouses = rentalHouseGeneratorService.loadRentalHousesFromFile();
			rentalHouses.forEach(house -> {
				var houseDocument = new Document("id :%s, city :%s, street :%s, house_number :%s, floor :%s, room_count :%s, price :%s".formatted(house.getId(), house.getCity(), house.getStreet(), house.getHouseNumber(), house.getFloor(), house.getRoomCount(), house.getPrice()));
				vectorStore.add(List.of(houseDocument));
			});
		}
	}
}
