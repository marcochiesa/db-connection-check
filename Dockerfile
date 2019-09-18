FROM frolvlad/alpine-java:jdk8-slim

ENTRYPOINT ["java", "-Xmx256m", "-jar","/app.jar"]
ADD build/libs/dbconncheck-0.0.1-SNAPSHOT.jar app.jar
RUN touch /app.jar
