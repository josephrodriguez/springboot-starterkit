FROM openjdk:8-jdk-alpine as basebuild
EXPOSE 8080

FROM basebuild as build
COPY . /app
WORKDIR /app
RUN chmod +x mvnw && ./mvnw package

ENTRYPOINT ["java","-jar","/app/target/learning-spring-boot-0.0.1.jar"]