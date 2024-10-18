package com.rental.rental_app.service;


import com.rental.rental_app.records.RentalSuggestion;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatClientService {

	private final ChatModel chatModel;
	private final VectorStore vectorStore;

	@Value("classpath:/prompts/question.st")
	private Resource questionSt;

	public RentalSuggestion question(String message) throws IOException {
		String messageGenerated = generateMessage(message);
		return ChatClient
			.builder(chatModel)
			.defaultSystem(messageGenerated)
			.build()
			.prompt()
			.call()
			.entity(RentalSuggestion.class);
	}


	private String generateMessage(String message) throws IOException {
		String template = loadTemplate();
		List<String> similarDocuments = findSimilarDocuments(message);
		String documentsString = similarDocuments.stream()
			.collect(Collectors.joining("\n"));
		return template
			.replace("{input}", message)
			.replace("{documents}", documentsString);
	}

	private String loadTemplate() throws IOException {
		try (var inputStream = questionSt.getInputStream();
			 var reader = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			return reader.lines().collect(Collectors.joining("\n"));
		}
	}

	private List<String> findSimilarDocuments(String message) {
		List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.query(message).withTopK(2));
		return similarDocuments.stream().map(Document::getContent).toList();
	}

}
