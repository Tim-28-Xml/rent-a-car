FROM openjdk:8-jdk-alpine
ADD target/ReportService-0.0.1-SNAPSHOT.jar ReportService.jar
ENTRYPOINT ["java", "-jar", "./ReportService.jar"]
EXPOSE 8091