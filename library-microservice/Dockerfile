# First stage: build the JAR
FROM maven:3.9.9 AS build
COPY . .
RUN mvn clean package -DskipTests

# Second stage: create the runtime image
FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY --from=build /target/*.jar library-service.jar
ENTRYPOINT ["java", "-jar", "library-service.jar"]
