# syntax=docker/dockerfile:1.2

FROM maven AS build

WORKDIR /app

# Copy only the pom.xml and download dependencies
COPY ./pom.xml /app
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline

# Copy the rest of the project files
COPY ./src /app/src

# Build the project
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

FROM openjdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
