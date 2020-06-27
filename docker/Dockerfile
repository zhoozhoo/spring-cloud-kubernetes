FROM adoptopenjdk:latest
LABEL maintainer="zhoozhoo@yahoo.com"
ARG JAR_FILE
WORKDIR /opt/app
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
