package com.rental.rental_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalMessageService {

	private final ChatModel chatModel;
	private final VectorStore vectorStore;


	public String question(String message) {
		return null;
	}
}
