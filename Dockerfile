#FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine
FROM --platform=linux/arm64/v8 eclipse-temurin:17-jdk
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]