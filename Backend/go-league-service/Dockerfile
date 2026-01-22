# Build Stage
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the common module
COPY common ./common

# Copy the user service module
COPY go-league-service ./go-league-service

# 1) Build & install common into the local Maven repo
# 2) Build the user service which now can resolve com.game.on:common
RUN mvn -f common/pom.xml clean install -DskipTests -q \
    && mvn -f go-league-service/pom.xml clean package -DskipTests -q

# Runtime Stage
FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S app && adduser -S app -G app
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build --chown=app:app /app/go-league-service/target/*.jar /app/app.jar

USER app
EXPOSE 8093
ENTRYPOINT ["java","-jar","/app/app.jar"]
