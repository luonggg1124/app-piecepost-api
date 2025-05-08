FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY ./services/mail/target/*.jar /app/mail.jar

ENTRYPOINT ["java", "-jar", "mail.jar"]
