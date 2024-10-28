#FROM openjdk:21-oracle
#VOLUME /tmp
#COPY target/*.jar /config-service.jar
#ENTRYPOINT [ "java", "-jar", "config-service.jar", "--spring.profiles.active=prod" ]
FROM maven:3.9.9 AS build
COPY . .
RUN mvn clean package -DskipTests

# Second stage: create the runtime image
FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY --from=build /target/*.jar config-service.jar
ENTRYPOINT ["java", "-jar", "config-service.jar"]