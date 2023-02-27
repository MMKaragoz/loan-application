FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/loanapp-0.0.1-SNAPSHOT.jar loanapp.jar
ENTRYPOINT ["java", "-jar", "loanapp.jar"]
