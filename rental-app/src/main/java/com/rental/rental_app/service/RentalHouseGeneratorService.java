package com.rental.rental_app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rental.rental_app.entity.RentalHouse;
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


	@Value("classpath:/docs/houses.json")
	private Resource houseFile;


	public List<RentalHouse> loadRentalHousesFromFile() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper.readValue(houseFile.getInputStream(), new TypeReference<>() {
		});
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
