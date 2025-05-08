FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY ./gateway/target/*.jar /app/gateway.jar

ENTRYPOINT ["java", "-jar", "gateway.jar"]
