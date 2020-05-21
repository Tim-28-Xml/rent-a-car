FROM openjdk:8-jdk-alpine
ADD target/AuthenticationService-0.0.1-SNAPSHOT.jar AuthenticationService.jar
ENTRYPOINT ["java", "-jar", "./AuthenticationService.jar"]
EXPOSE 8086