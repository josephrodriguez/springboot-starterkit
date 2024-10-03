FROM amazoncorretto:23-alpine-jdk@sha256:6d2a7b42ea97c88639b02852f6459f81289a1d9875b6cd6698a2c272aa0d6c94 as build
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw && ./mvnw clean package

FROM amazoncorretto:23.0.0-alpine@sha256:6d2a7b42ea97c88639b02852f6459f81289a1d9875b6cd6698a2c272aa0d6c94
RUN mkdir /app
COPY --from=build /usr/app/target/*.jar /app/com.springboot.starterkit.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/com.springboot.starterkit.jar"]
