# Use stable base image
FROM openjdk:17-slim

# Set working directory inside container
WORKDIR /app

# Copy JAR from local build folder to container
COPY target/payment_service-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]