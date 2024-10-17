FROM azul/zulu-openjdk:23-jdk-crac@sha256:07a0009444d68e6904ba661ac88dbb1dc1ab8cc483527645c7a25a7ac79eedab as build
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw && ./mvnw clean package

FROM azul/zulu-openjdk:23-jre-headless@sha256:76dda154934ea97ea393462d3b3089aec04a727a731753b7ca320b1af5bf9551
RUN mkdir /app
COPY --from=build /usr/app/target/*.jar /app/com.springboot.starterkit.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/com.springboot.starterkit.jar"]
