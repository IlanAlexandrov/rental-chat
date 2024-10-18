package com.rental.rental_app.service;

import com.rental.rental_app.entity.RentalHouse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalHouseGeneratorService {

	private final RentalHouseService rentalHouseRepository;

	@Value("classpath:/docs/houses.st")
	private Resource houseFile;


	public void loadRentalHousesFromFile() throws IOException {
		List<RentalHouse> rentalHouses = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(houseFile.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				RentalHouse rentalHouse = parseLineToRentalHouse(line);
				rentalHouses.add(rentalHouse);
			}
			rentalHouseRepository.saveHouses(rentalHouses);
		}
	}

	private RentalHouse parseLineToRentalHouse(String line) {
		RentalHouse rentalHouse = new RentalHouse();
		String[] fields = line.split(", ");
		for (String field : fields) {
			String[] keyValue = field.split(":");
			switch (keyValue[0].trim()) {
				case "city":
					rentalHouse.setCity(keyValue[1].trim());
					break;
				case "street":
					rentalHouse.setStreet(keyValue[1].trim());
					break;
				case "house_number":
					rentalHouse.setHouseNumber(Integer.parseInt(keyValue[1].trim()));
					break;
				case "floor":
					rentalHouse.setFloor(Integer.parseInt(keyValue[1].trim()));
					break;
				case "room_count":
					rentalHouse.setRoomCount(Integer.parseInt(keyValue[1].trim()));
					break;
				case "price":
					rentalHouse.setPrice(Integer.parseInt(keyValue[1].trim()));
					break;
				case "area":
					rentalHouse.setArea(Integer.parseInt(keyValue[1].trim()));
					break;
			}
		}
		return rentalHouse;
	}
}
