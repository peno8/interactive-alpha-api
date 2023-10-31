# Interactive-Alpha-API

Backend REST API server of [Interactive-Alpha](https://interactive-alpha.com/)

## Consists of

Java, Spring Boot WebFlux

## Build
```
./mvnw install -DskipTests

docker build --platform linux/arm64 -t xxxxxxxx/interactive-alpha-api:0.0.x .

docker push xxxxxxxx/interactive-alpha-api:0.0.x
```