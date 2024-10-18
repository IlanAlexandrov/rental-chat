package com.rental.rental_app.controller;

import com.rental.rental_app.records.RentalSuggestion;
import com.rental.rental_app.service.ChatClientService;
import com.rental.rental_app.service.RentalMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/rental")
@RequiredArgsConstructor
public class RentalMessageController {

	private final RentalMessageService rentalMessageService;
	private final ChatClientService chatClientService;

	@GetMapping("/message")
	public ResponseEntity<RentalSuggestion> handleMessage(@RequestParam(value = "message", defaultValue = "Who are you?") String message) throws IOException {
		return ResponseEntity.ok(chatClientService.question(message));
	}
}
