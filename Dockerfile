# Dockerfile
FROM openjdk:21-jdk-slim

# Argument for the JAR file name with version
ARG JAR_FILE=build/libs/task-manager-1.0.0.jar

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Expose the application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]