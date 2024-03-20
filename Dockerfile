#FROM gradle:7-jdk11 AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle buildFatJar --no-daemon
#
#FROM openjdk:11
#EXPOSE 8080:8080
#RUN mkdir /app
#COPY --from=build /home/gradle/src/build/libs/*.jar /app/com.nrup.ktor.ktor-sample-app-all.ja
#ENTRYPOINT ["java", "-jar", "com.nrup.ktor.ktor-sample-app-all.jar"]


FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/com.nrup.ktor.ktor-sample-app-all.jar
ENTRYPOINT ["java","-jar","/app/com.nrup.ktor.ktor-sample-app-all.jar"]