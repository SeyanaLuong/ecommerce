    # Build Stage
    FROM maven:3-openjdk-17 AS build
    WORKDIR /app
    COPY pom.xml .
    COPY src src
    RUN mvn clean package -DskipTests

    # Runtime Stage
    FROM openjdk:17-jre-slim
    WORKDIR /app
    COPY --from=build /app/target/*.jar app.jar
    ENTRYPOINT ["java", "-jar", "app.jar"]
    EXPOSE 8080 # Or your application's port
