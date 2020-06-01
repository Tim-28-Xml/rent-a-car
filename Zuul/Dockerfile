FROM openjdk:8-jdk-alpine
ADD target/Zuul-0.0.1-SNAPSHOT.jar Zuul.jar
ENTRYPOINT ["java", "-jar", "./Zuul.jar"]
EXPOSE 8443