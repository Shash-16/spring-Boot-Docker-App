FROM openjdk:21
LABEL authors="shashankshekhar"
EXPOSE 9001
ADD target/cfworker:1.0.jar cfworker.jar
ENTRYPOINT ["java", "-jar", "cfworker.jar"]