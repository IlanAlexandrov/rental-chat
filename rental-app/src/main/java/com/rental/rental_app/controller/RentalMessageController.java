package com.rental.rental_app.controller;

import com.rental.rental_app.service.RentalMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rental")
@RequiredArgsConstructor
public class RentalMessageController {

	private final RentalMessageService rentalMessageService;

	@GetMapping("/message")
	public ResponseEntity<String> handleMessage(@RequestParam(value = "message", defaultValue = "Who are you?") String message) {
		return ResponseEntity.ok(rentalMessageService.question(message));
	}
}
