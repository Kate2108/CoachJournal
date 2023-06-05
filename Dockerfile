
FROM maven:3.6.3-openjdk-17-slim AS build
COPY src /home/application/src
COPY pom.xml /home/application/
USER root
RUN --mount=type=cache,target=/root/.m2 mvn -DskipTests=true -f /home/application/pom.xml clean package

FROM openjdk:17
COPY --from=build /home/application/target/SemesterWorkSpring-0.0.1-SNAPSHOT.war user/local/lib/SemesterWorkSpring-0.0.1-SNAPSHOT.war
EXPOSE 80
ENTRYPOINT ["java","-jar","user/local/lib/SemesterWorkSpring-0.0.1-SNAPSHOT.war"]