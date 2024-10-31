# Use a specific version for reproducibility
FROM azul/zulu-openjdk:23-jdk-crac AS build

# Set working directory
WORKDIR /usr/app

# Copy only the necessary files for dependency resolution
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
COPY src src

# Grant execution rights and build the application
RUN chmod +x gradlew && ./gradlew build --no-daemon

# Use a smaller JRE image for the runtime
FROM azul/zulu-openjdk:23-jre-headless

# Create the application directory
RUN mkdir /app

# Copy the JAR from the build stage
COPY --from=build /usr/app/build/libs/*.jar /app/app.jar

# Expose the application's port
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
