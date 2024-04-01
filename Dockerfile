# Use a imagem oficial do Java 17
FROM openjdk:17-jdk-slim

# Argumentos para o JAR
ARG JAR_FILE=target/*.jar

# Copie o JAR da sua aplicação para a imagem
COPY ${JAR_FILE} app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]


# Build stage
FROM maven:3.8.2-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:17-jdk-slim
COPY --from=build /target/Backend-BackendCodGroup-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]

