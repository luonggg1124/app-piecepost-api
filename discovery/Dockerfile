FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY ./discovery/target/*.jar /app/discovery.jar

ENTRYPOINT ["java", "-jar", "discovery.jar"]
