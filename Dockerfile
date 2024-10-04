FROM azul/zulu-openjdk:23-jdk-crac@sha256:4b6c7146f80d4758a1d33823795086be718983932a0b9534dcdc8c813e0661c7 as build
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw && ./mvnw clean package

FROM azul/zulu-openjdk:23-jre-headless@sha256:2bc348d73bce5ef0928e73413f1836d90614e8bb95e2b4ac108cb385f7f73c9d
RUN mkdir /app
COPY --from=build /usr/app/target/*.jar /app/com.springboot.starterkit.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/com.springboot.starterkit.jar"]
