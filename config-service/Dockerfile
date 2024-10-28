FROM openjdk:21-oracle
VOLUME /tmp
COPY target/*.jar /config-service.jar
ENTRYPOINT [ "java", "-jar", "config-service.jar", "--spring.profiles.active=prod" ]