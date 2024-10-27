# First stage: build the JAR
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Second stage: create the runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/library-microservice-0.0.1-SNAPSHOT.jar library-service.jar
EXPOSE 9892
ENTRYPOINT ["java", "-jar", "/app/library-service.jar"]
