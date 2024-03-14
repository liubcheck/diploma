FROM openjdk:17-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eureka-server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/eureka-server.jar"]