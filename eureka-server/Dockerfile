FROM openjdk:21-oracle
VOLUME /tmp
COPY target/*.jar /eureka-server.jar
ENTRYPOINT [ "java", "-jar", "eureka-server.jar", "--spring.profiles.active=prod" ]
