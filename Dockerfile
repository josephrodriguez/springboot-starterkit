FROM azul/zulu-openjdk:23-jdk-crac@sha256:31e1e52f269cf35e9ce8b25babb348d5f049f2f5681e5880777b43366c414733 as build
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw && ./mvnw clean package

FROM azul/zulu-openjdk:23-jre-headless@sha256:352c7db1e0a7fdcc45b0349b214fae1e0412155431a5610c1183bde5e61fdeb5
RUN mkdir /app
COPY --from=build /usr/app/target/*.jar /app/com.springboot.starterkit.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/com.springboot.starterkit.jar"]
