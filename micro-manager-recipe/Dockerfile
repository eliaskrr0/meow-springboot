FROM eclipse-temurin:21-jdk as build
WORKDIR /app
COPY common-dto common-dto
COPY micro-manager-recipe micro-manager-recipe
RUN chmod +x common-dto/mvnw micro-manager-recipe/mvnw \
    && common-dto/mvnw install -DskipTests \
    && micro-manager-recipe/mvnw package -DskipTests

FROM eclipse-temurin:21-jre-alpine
COPY --from=build micro-manager-recipe/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]