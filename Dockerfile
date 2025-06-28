# Use Eclipse Temurin Java 21 base image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# Grant execute permission for mvnw
RUN chmod +x mvnw

# Build the application (this creates the .jar file)
RUN ./mvnw clean package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8080

# Run the Spring Boot .jar file
CMD ["java", "-jar", "target/*.jar"]
