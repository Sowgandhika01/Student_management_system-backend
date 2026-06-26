# Build stage
FROM maven:3.9.14-eclipse-temurin-21 as build
WORKDIR /workspace

COPY pom.xml ./
COPY .mvn .mvn
COPY src src

RUN mvn -DskipTests package -DskipDocs

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/target/helloapp-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
