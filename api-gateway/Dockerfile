#FROM openjdk:21-jdk-oracle
#VOLUME /tmp
#COPY target/*.jar /api-gateway.jar
#CMD [ "java", "-jar", "api-gateway.jar"]
FROM maven:3.9.9 AS build
COPY . .
RUN mvn clean package -DskipTests

# Second stage: create the runtime image
FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY --from=build /target/*.jar api-gateway.jar
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]