FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-historico.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/api-historico.jar"]
