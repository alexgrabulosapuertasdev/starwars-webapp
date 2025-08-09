FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/starwarsbackend-0.2.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
