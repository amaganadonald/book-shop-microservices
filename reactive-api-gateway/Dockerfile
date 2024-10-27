FROM openjdk:23-jdk-oracle
COPY target/reactive-api-gateway-0.0.1-SNAPSHOT.jar /api-gateway.jar
EXPOSE 9890
CMD [ "java", "-jar", "api-gateway.jar"]