FROM openjdk:11-jre-slim
MAINTAINER Fakher SOUAYEH
COPY build/libs/rest-service-1.1.0-SNAPSHOT.jar demo-backend-1.1.0.jar
ENTRYPOINT ["java","-jar","/demo-backend-1.1.0.jar"]