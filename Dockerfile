# Use the official Maven image to build the application
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the working directory
COPY pom.xml .
COPY src ./src

# Run Maven to package the application (skip tests for faster builds if already tested)
RUN mvn clean package -DskipTests

# Use a lightweight JDK image for the runtime
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory for the runtime
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/iot-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's default port
EXPOSE 8080

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
