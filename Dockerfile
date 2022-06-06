FROM eclipse-temurin:17-jre
ARG JAR_FILE=build/libs/xmen-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]