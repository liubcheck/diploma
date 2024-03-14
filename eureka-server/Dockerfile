FROM maven:3.8.4-openjdk-17-slim AS build
COPY pom.xml .
COPY src src
RUN mvn -f ./eureka-server/pom.xml clean package

FROM openjdk:17-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eureka-server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/eureka-server.jar"]