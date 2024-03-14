FROM openjdk:17-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} lesson-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/lesson-service.jar"]