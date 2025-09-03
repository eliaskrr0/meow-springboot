FROM eclipse-temurin:21-jdk as build
WORKDIR /app
COPY common-dto common-dto
COPY micro-manager-food micro-manager-food
RUN chmod +x common-dto/mvnw micro-manager-food/mvnw \
    && common-dto/mvnw install -DskipTests \
    && micro-manager-food/mvnw package -DskipTests

FROM eclipse-temurin:21-jre-alpine
COPY --from=build micro-manager-food/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]