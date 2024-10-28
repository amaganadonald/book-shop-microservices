FROM openjdk:21-jdk-oracle
VOLUME /tmp
COPY target/*.jar /api-gateway.jar
CMD [ "java", "-jar", "api-gateway.jar"]