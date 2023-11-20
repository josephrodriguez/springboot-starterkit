FROM eclipse-temurin:21-jdk-alpine as build
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw && ./mvnw clean package

FROM eclipse-temurin:21-jre-alpine
RUN apk update && apk upgrade && mkdir /app
COPY --from=build /usr/app/target/*.jar /app/com.springboot.starterkit.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/com.springboot.starterkit.jar"]
