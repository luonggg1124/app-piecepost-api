FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY ./services/user/target/*.jar /app/user.jar

ENTRYPOINT ["java", "-jar", "user.jar"]
