FROM openjdk:8-jdk-alpine
ADD target/PricelistService-0.0.1-SNAPSHOT.jar PricelistService.jar
ENTRYPOINT ["java", "-jar", "./PricelistService.jar"]
EXPOSE 8077