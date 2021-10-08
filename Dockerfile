FROM openjdk:8

RUN apt-get update && apt-get install -y maven
COPY . /project
RUN  cd /project && mvn package
EXPOSE 8080

#run the spring boot application
ENTRYPOINT ["java","-jar","/project/target/gerimedica-0.0.1.jar"]