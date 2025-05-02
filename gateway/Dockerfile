FROM openjdk:25-ea-jdk

WORKDIR /app

COPY target/gateway-*.jar app.jar

EXPOSE 4001

ENTRYPOINT ["java", "-jar", "app.jar"]