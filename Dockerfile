#
#FROM openjdk:21-jdk-alpine
#
## Set the working directory inside the container
#WORKDIR /app
#
## Copy the classes.jar file from the project directory to the container
#COPY target/classes.jar /app/test1.jar
#
## Expose port 9090 (assuming your application listens on this port)
#EXPOSE 9090
#
## Define the command to run your application
#CMD ["java", "-jar", "test1.jar"

#
#FROM openjdk:17-jdk-alpine
#EXPOSE 9090
#ARG JAR_FILE=target/target.jar
#ADD ${JAR_FILE} app.jar
# Use AdoptOpenJDK OpenJDK 11 as the base image
#
#FROM adoptopenjdk/openjdk11:alpine-jre
#
#
#WORKDIR /app
#
#
#COPY target/target.jar /test1-hotel/test1-hotel.jar
#
#
#EXPOSE 9090
#
## Install necessary utilities
#RUN apk add --no-cache curl jq
#
## Copy the wait-for-postgres.sh script into the container at /wait-for-postgres.sh
#COPY wait-for-postgres.sh /wait-for-postgres.sh
#
## Make the script executable
#RUN chmod +x /wait-for-postgres.sh
#
## Specify the command to run your Spring Boot application when the container starts
#CMD ["/bin/sh", "-c", "/wait-for-postgres.sh && java -jar app.jar"]



FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /opt/app

ARG JAR_FILE=target/spring-boot-web.jar

# cp spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar



