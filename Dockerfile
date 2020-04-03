# Docker file for two phase build
# Phase 1 - Build the application in it's own container named "build"
FROM openjdk:8-jdk-alpine as build
VOLUME /tmp
COPY . .
RUN ./gradlew clean build

# Phase 2 - Build the actual docker container with only the jar file
FROM openjdk:8-jdk-alpine
WORKDIR /app
# Copy file from the "build container identified in line 3
COPY --from=build build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
# Build docker image
# $ docker build -t movieservice .
#
# Deploy movie service locally
# $ run -p 8080:8080 movieservice
# should be available at http://localhost:8080/api/jokes
#
# Push to dockerhub
# $ docker login
# $ docker tag jokeservice krishnakarki/movieservice
# $ docker push krishnakarki/movieservice
#
# Retrieve the image from docker hub
# $ docker pull krishnakarki/movieservice