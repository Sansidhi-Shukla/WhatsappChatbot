package com.example.project.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

@Service
public class WhatsAppService {

	public void sendMessage(String recipientNumber, String messageText) {
		String url = "https://graph.facebook.com/v18.0/<PHONE_NUMBER_ID>/messages";

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer YOUR_ACCESS_TOKEN");
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> body = new HashMap<>();
		body.put("messaging_product", "whatsapp");
		body.put("to", recipientNumber);
		body.put("type", "text");
		body.put("text", Map.of("body", messageText));

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity(url, request, String.class);
	}
}

