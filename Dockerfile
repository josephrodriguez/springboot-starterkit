FROM azul/zulu-openjdk:23-jdk-crac@sha256:57695039a4614227840a7a24987eacec233447968c2c54c9e926751b850af04e as build
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw && ./mvnw clean package

FROM azul/zulu-openjdk:23-jre-headless@sha256:897b96db593cf007b9638710734c00d8e692c032698091055734b6c3f78efb01
RUN mkdir /app
COPY --from=build /usr/app/target/*.jar /app/com.springboot.starterkit.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/com.springboot.starterkit.jar"]
