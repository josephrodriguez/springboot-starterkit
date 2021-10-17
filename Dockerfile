FROM openjdk:8-jdk-alpine
COPY . /project
WORKDIR project
RUN  chmod +x mvnw &&./mvnw --version && ./mvnw package
RUN cd ./target && ls
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} learning-spring-boot.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/learning-spring-boot.jar"]