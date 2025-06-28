package com.example.project.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
	@PostConstruct
	public void initialize() {
		try {
			InputStream serviceAccount = getClass().getClassLoader()
					.getResourceAsStream("firebase_key.json");

			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			FirebaseApp.initializeApp(options);
			System.out.println("✅ Firebase initialized successfully.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

