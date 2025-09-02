FROM eclipse-temurin:21-jdk as build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw package -DskipTests

FROM eclipse-temurin:21-jre-alpine
COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
