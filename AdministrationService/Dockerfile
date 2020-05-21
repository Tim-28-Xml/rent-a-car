FROM openjdk:8-jdk-alpine
ADD target/AdministrationService-0.0.1-SNAPSHOT.jar AdministrationService.jar
ENTRYPOINT ["java", "-jar", "./AdministrationService.jar"]
EXPOSE 8084