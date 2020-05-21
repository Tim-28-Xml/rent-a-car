FROM openjdk:8-jdk-alpine
ADD target/ReviewService-0.0.1-SNAPSHOT.jar ReviewService.jar
ENTRYPOINT ["java", "-jar", "./ReviewService.jar"]
EXPOSE 8092