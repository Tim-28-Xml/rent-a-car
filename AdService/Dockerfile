FROM openjdk:8-jdk-alpine
ADD target/AdService-0.0.1-SNAPSHOT.jar AdService.jar
ENTRYPOINT ["java", "-jar", "./AdService.jar"]
EXPOSE 8084