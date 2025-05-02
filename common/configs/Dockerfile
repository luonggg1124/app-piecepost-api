FROM openjdk:25-ea-jdk

WORKDIR /app

COPY target/common-*.jar app.jar



ENTRYPOINT ["java", "-jar", "app.jar"]