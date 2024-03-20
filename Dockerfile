#FROM openjdk:11.0.16
#EXPOSE 8080:8080
#RUN mkdir /app
#COPY ./build/libs/*-all.jar /app/com.nrup.ktor.ktor-sample-app-all.jar
#ENTRYPOINT ["java","-jar","/app/com.nrup.ktor.ktor-sample-app-all.jar"]

# Base image with OpenJDK 17
FROM openjdk:17-slim

# Set working directory
WORKDIR /app

# Copy application code and build script
COPY . .

# Download dependencies (adjust for Gradle or Maven)
RUN ./gradlew downloadDependencies

# Build the application (adjust for your build command)
RUN ./gradlew build

# Expose the application port
EXPOSE 9000

# Environment variables for database connection (replace placeholders)
ENV DB_HOST=db
ENV DB_PORT=5432
ENV DB_NAME=mydb
ENV DB_USER=postgres
## NEVER store passwords directly in Dockerfile or Compose!
#ENV DB_PASSWORD=1234

# Start the application using the main class (replace with your actual class)
CMD ["java", "-jar", "build/libs/com.nrup.ktor.ktor-sample-app-all.jar"]

