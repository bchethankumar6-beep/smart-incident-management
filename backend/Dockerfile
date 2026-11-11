FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/incident-management-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-Duser.timezone=Asia/Kolkata", "-jar", "app.jar"]
