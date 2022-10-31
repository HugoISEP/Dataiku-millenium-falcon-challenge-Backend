FROM openjdk:19-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY target/classes/millennium-falcon.json  millennium-falcon.json
COPY target/classes/universe.db  universe.db
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
