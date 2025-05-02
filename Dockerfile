FROM openjdk:25-ea-jdk

WORKDIR /app

COPY .  app.jar



ENTRYPOINT ["java", "-jar", "app.jar"]