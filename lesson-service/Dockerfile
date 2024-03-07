FROM openjdk:17-slim-buster
COPY target/*.jar lesson-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/lesson-service.jar"]
