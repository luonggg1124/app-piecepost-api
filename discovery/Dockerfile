FROM openjdk:25-ea-jdk

WORKDIR /app

COPY target/discovery-*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]