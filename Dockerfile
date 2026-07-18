FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy only the pom.xml and download dependencies
COPY ./pom.xml /app
RUN mvn --batch-mode --no-transfer-progress dependency:go-offline

# Copy the rest of the project files
COPY ./src /app/src

# Build the project
RUN mvn --batch-mode --no-transfer-progress clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
