package com.example.project.controller;

import com.example.project.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WhatsAppController {

	@Autowired
	private ChatService chatService;

	// WhatsApp webhook verification (required by Meta)
	@GetMapping
	public ResponseEntity<String> verify(@RequestParam("hub.mode") String mode,
										 @RequestParam("hub.challenge") String challenge,
										 @RequestParam("hub.verify_token") String token) {
		if ("my_verify_token".equals(token)) {
			return ResponseEntity.ok(challenge);
		} else {
			return ResponseEntity.status(403).body("Verification failed");
		}
	}

	// This endpoint will receive incoming messages from WhatsApp
	@PostMapping
	public ResponseEntity<String> receiveMessage(@RequestBody Map<String, Object> payload) {
		try {
			// Navigate the nested JSON payload
			Map entry = ((Map)((java.util.List)payload.get("entry")).get(0));
			Map changes = ((Map)((java.util.List)entry.get("changes")).get(0));
			Map value = (Map) changes.get("value");
			Map messageObj = ((Map)((java.util.List)value.get("messages")).get(0));

			String userId = (String) messageObj.get("from"); // phone number ID
			Map text = (Map) messageObj.get("text");
			String message = (String) text.get("body");

			// Pass the message to ChatService
			chatService.handleIncomingMessage(userId, message);

			return ResponseEntity.ok("Message processed");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error processing message");
		}
	}
}


