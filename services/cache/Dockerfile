FROM openjdk:25-ea-jdk

WORKDIR /app

COPY target/cache-*.jar app.jar

EXPOSE 50051

ENTRYPOINT ["java", "-jar", "app.jar"]