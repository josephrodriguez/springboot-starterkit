FROM azul/zulu-openjdk:23-jdk-crac@sha256:d763078cc533d0b7ca63904bc1918067e49e408266be6fc4219c70f257fe475c as build
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw && ./mvnw clean package

FROM azul/zulu-openjdk:23-jre-headless@sha256:09eabbd480182779ea823a7d4e8a7a434fd2dc9a6084cfe84b7d038a63e2f074
RUN mkdir /app
COPY --from=build /usr/app/target/*.jar /app/com.springboot.starterkit.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/com.springboot.starterkit.jar"]
