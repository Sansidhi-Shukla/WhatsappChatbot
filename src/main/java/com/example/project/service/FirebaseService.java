package com.example.project.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseService {

	public void saveUserMessage(String userId, String message) throws Exception {
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference docRef = db.collection("users").document(userId);

		Map<String, Object> data = new HashMap<>();
		data.put("message", message);
		data.put("timestamp", System.currentTimeMillis());

		// Save data to Firestore
		ApiFuture<WriteResult> result = docRef.set(data);

		// Optionally wait for operation to complete
		System.out.println("Update time : " + result.get().getUpdateTime());
	}

	public String getLastUserMessage(String userId) throws Exception {
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference docRef = db.collection("users").document(userId);

		return docRef.get().get().getString("message");
	}
}

