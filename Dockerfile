FROM azul/zulu-openjdk:23-jdk-crac@sha256:516b23e20eddf9b88706fe4219e11acfb36a19c3fd41e09d995a268d641c76c3 as build
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw && ./mvnw clean package

FROM azul/zulu-openjdk:23-jre-headless@sha256:34b5f7bd1b9b3533a5ca1c28ac7baaefd7184ed70baa4c924a0c376f6bb0ee1c
RUN mkdir /app
COPY --from=build /usr/app/target/*.jar /app/com.springboot.starterkit.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/com.springboot.starterkit.jar"]
