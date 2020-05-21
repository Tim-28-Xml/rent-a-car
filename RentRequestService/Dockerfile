FROM openjdk:8-jdk-alpine
ADD target/RentRequestService-0.0.1-SNAPSHOT.jar RentRequestService.jar
ENTRYPOINT ["java", "-jar", "./RentRequestService.jar"]
EXPOSE 8090