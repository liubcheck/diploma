FROM openjdk:17-slim-buster
COPY target/*.jar eureka-server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]
