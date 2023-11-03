FROM openjdk:21
LABEL authors="shashankshekhar"
EXPOSE 8080
ADD target/cfapp:1.0.jar cfapp.jar
ENTRYPOINT ["java", "-jar", "cfapp.jar"]