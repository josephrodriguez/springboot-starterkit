FROM azul/zulu-openjdk:23-jdk-crac@sha256:09ddf8f882931f0050710815c840e2a310f025df1a3aa7f7b52a0394b4a01060 as build
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw && ./mvnw clean package

FROM azul/zulu-openjdk:23-jre-headless@sha256:a72fdb505fbf87f84532e4fb13ab5738504d2ee014d41af21091fcbe9cd63f98
RUN mkdir /app
COPY --from=build /usr/app/target/*.jar /app/com.springboot.starterkit.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/com.springboot.starterkit.jar"]
