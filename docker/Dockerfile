FROM openjdk:11-jre as builder
WORKDIR application
ARG JAR_FILE
ADD ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

ARG DOCKERIZE_VERSION
RUN wget https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-linux-amd64-${DOCKERIZE_VERSION}.tar.gz \
    && tar xzf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && chmod +x dockerize

FROM adoptopenjdk:14-jre-hotspot
LABEL maintainer="zhoozhoo@yahoo.com"
WORKDIR application
ARG EXPOSED_PORT
EXPOSE ${EXPOSED_PORT}
COPY --from=builder application/dockerize ./
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
