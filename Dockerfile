# Start with a base image containing Java runtime
FROM openjdk

# Set the working directory to /app
WORKDIR /app

# Copy the application JAR file to the container
COPY target/BackendIntegrationProject-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application listens on
EXPOSE 8080

# Run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
