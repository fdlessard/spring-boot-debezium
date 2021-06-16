# Start with a base image containing Java runtime
FROM openjdk:15.0.2
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080/tcp

# The application's jar file
ARG JAR_FILE=./build/libs/customer-api-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} customer-api.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/customer-api.jar"]
