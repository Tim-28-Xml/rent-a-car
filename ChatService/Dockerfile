FROM openjdk:8-jdk-alpine
ADD target/ChatService-0.0.1-SNAPSHOT.jar ChatService.jar
ENTRYPOINT ["java", "-jar", "./ChatService.jar"]
EXPOSE 8087