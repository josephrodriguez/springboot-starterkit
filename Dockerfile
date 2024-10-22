FROM azul/zulu-openjdk:23-jdk-crac@sha256:04d4cf3c621bf696dfba4f3c0b5a623a642d20c6e4407a76be1c1210d3844570 as build
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw && ./mvnw clean package

FROM azul/zulu-openjdk:23-jre-headless@sha256:3f441183d90d0733a3edc7f7d05997765bcb9e61ac0357ebac62cb4f7b501581
RUN mkdir /app
COPY --from=build /usr/app/target/*.jar /app/com.springboot.starterkit.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/com.springboot.starterkit.jar"]
