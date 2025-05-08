FROM eclipse-temurin:21-jdk
WORKDIR /app


COPY ./services/cache/target/*.jar /app/cache.jar

ENTRYPOINT ["java", "-jar", "cache.jar"]
