package com.rental.rental_app.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity()
@Table(name = "token_history")
@Getter
@Setter
public class RentalHouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incremented id
	private int id;
	private String city;
	private String street;
	private int houseNumber;
	private int floor;
	private int roomCount;
	private int price;
	private int area;
}
