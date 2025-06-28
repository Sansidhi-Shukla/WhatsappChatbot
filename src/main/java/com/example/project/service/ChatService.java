package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ChatService {

	private static final String WHATSAPP_API_URL = "https://graph.facebook.com/v19.0/{phoneNumberId}/messages";

	@Value("${whatsapp.phone-number-id}")
	private String phoneNumberId;

	@Value("${whatsapp.access-token}")
	private String accessToken;

	@Autowired
	private WhatsAppService whatsAppService;

	@Autowired
	private FirebaseService firebaseService;

	@Autowired
	private RestTemplate restTemplate;

	public void handleIncomingMessage(String userId, String message) {
		try {
			if (message.equalsIgnoreCase("last")) {
				// Retrieve last message from Firebase
				String lastMessage = firebaseService.getLastUserMessage(userId);

				if (lastMessage != null) {
					whatsAppService.sendMessage(userId, "Your last message was: " + lastMessage);
				} else {
					whatsAppService.sendMessage(userId, "No message history found.");
				}

			} else {
				// Save current message to Firebase
				firebaseService.saveUserMessage(userId, message);

				// Echo back
				whatsAppService.sendMessage(userId, "Message received: " + message);
			}

		} catch (Exception e) {
			e.printStackTrace();
			whatsAppService.sendMessage(userId, "Something went wrong. Please try again.");
		}
	}

	public void sendMessage(String recipientPhone, String textMessage) {
		String url = WHATSAPP_API_URL.replace("{PHONE_NUMBER_ID}", phoneNumberId);

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> text = Map.of("body", textMessage);
		Map<String, Object> body = Map.of(
				"messaging_product", "whatsapp",
				"to", recipientPhone,
				"type", "text",
				"text", text
		);

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
		try {
			restTemplate.postForEntity(url, request, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

