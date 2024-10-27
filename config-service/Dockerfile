FROM openjdk:23-jdk-oracle
COPY target/config-service-0.0.1-SNAPSHOT.jar /config-service.jar
EXPOSE 9890
CMD [ "java", "-jar", "config-service.jar", "--spring.profiles.active=prod"]