#FROM gradle:7-jdk11 AS builder
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle buildFatJar --no-daemon
#
#FROM openjdk:11
#EXPOSE 8080:8080
#RUN mkdir /app
#COPY --from=build /home/gradle/src/build/libs/*.jar /app/com.nrup.ktor.ktor-sample-app-all.ja
#ENTRYPOINT ["java", "-jar", "com.nrup.ktor.ktor-sample-app-all.jar"]

# Set working directory
WORKDIR /app

# Copy application code
COPY . .

# Download dependencies using Gradle wrapper (adjust if using Maven)
RUN ./gradlew downloadDependencies

# Build the application (replace with your build command if different)
RUN ./gradlew build

# Expose port (adjust port number if needed)
EXPOSE 8080

# Start the application using the main class (replace with your actual class)
CMD ["java", "-jar", "build/libs/com.nrup.ktor.ktor-sample-app-all.jar"]