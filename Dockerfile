FROM openjdk:8-jdk-alpine
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw \
    && ./mvnw --version \
    && ./mvnw package
COPY /target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]