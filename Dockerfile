# Use Eclipse Temurin Java 21 base image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy Maven wrapper and project files
COPY . .

# Grant execute permission for mvnw
RUN chmod +x mvnw

# Build the application (this creates the .jar file)
RUN ./mvnw clean package -DskipTests

# -------------------------------
# Second stage: runtime
# -------------------------------
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the Spring Boot .jar file
CMD ["java", "-jar", "target/*.jar"]
