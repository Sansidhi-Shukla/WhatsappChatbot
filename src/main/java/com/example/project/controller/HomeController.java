package com.example.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "Welcome to the WhatsApp Chatbot API!";
	}

	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}
}

