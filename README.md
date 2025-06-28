# WhatsApp Chatbot using Spring Boot and Firebase

This project is a backend service for a WhatsApp chatbot that integrates with Meta's WhatsApp Cloud API. It is built using **Spring Boot** and connects to **Firebase** for storing user/chat data.

---

## 🚀 Features

- Send and receive messages using WhatsApp Cloud API
- Handle webhook callbacks
- Store messages or user data in Firebase
- Modular service-oriented code structure

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Firebase Admin SDK
- Maven
- Docker (for deployment)
- Meta WhatsApp Cloud API

---

## 📂 Project Structure

```
src/
├── main/
│ ├── java/com/example/project/
│ │ ├── config/ # Firebase configuration
│ │ ├── controller/ # WhatsApp webhook controller
│ │ ├── service/ # Business logic
│ │ └── ProjectApplication.java # Main class
│ └── resources/
│ └── application.properties
```

---

## ⚙️ Setup Instructions

### ✅ Prerequisites

- Java 21
- Maven
- Firebase Project with service account key
- WhatsApp Business API access with webhook configured

---

### 🧪 Local Development

1. **Clone the repository:**
   ```
   git clone https://github.com/Sansidhi-Shukla/WhatsappChatbot.git
   cd WhatsappChatbot
   ```
   
2. **Add Firebase Service Account Key:**
  Save the Firebase key JSON in:
  ```
    src/main/resources/firebase_key.json
  ```

3. **Update application.properties:**
  ```
  server.port=8080
  ```

4. **Run the application:**
  ```
  ./mvnw spring-boot:run
  ```
