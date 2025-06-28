package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	@Autowired
	private WhatsAppService whatsAppService;

	@Autowired
	private FirebaseService firebaseService;
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
}

