FROM openjdk:8-jdk-alpine as basebuild
EXPOSE 8080

FROM basebuild as build
COPY . /app
WORKDIR /app
RUN chmod +x mvnw && ./mvnw package
COPY /target/learning-spring-boot-0.0.1.jar /app.jar
ENTRYPOINT ["java","-jar","app.jar"]