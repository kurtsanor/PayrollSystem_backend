# Start from a Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy Maven/Gradle build files and source code
COPY ./target/springboot-1.0-SNAPSHOT.jar app.jar



# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot jar
ENTRYPOINT ["java", "-jar", "app.jar"]
