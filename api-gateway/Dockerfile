FROM maven:3.8.4-openjdk-17-slim AS build
COPY pom.xml .
COPY src src
RUN mvn -f ./api-gateway/pom.xml clean package

FROM openjdk:17-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/api-gateway.jar"]
