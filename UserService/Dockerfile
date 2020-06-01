FROM openjdk:8-jdk-alpine
ADD target/UserService-0.0.1-SNAPSHOT.jar UserService.jar
ENTRYPOINT ["java", "-jar", "./UserService.jar"]
EXPOSE 8085