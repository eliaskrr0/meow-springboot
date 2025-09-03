FROM eclipse-temurin:21-jdk as build
WORKDIR /app
COPY common-dto common-dto
COPY micro-user-goal micro-user-goal
RUN chmod +x common-dto/mvnw micro-user-goal/mvnw \
    && common-dto/mvnw install -DskipTests \
    && micro-user-goal/mvnw package -DskipTests

FROM eclipse-temurin:21-jre-alpine
COPY --from=build micro-user-goal/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]