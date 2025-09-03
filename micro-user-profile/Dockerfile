FROM eclipse-temurin:21-jdk as build
WORKDIR /app
COPY common-dto common-dto
COPY micro-user-profile micro-user-profile
RUN chmod +x common-dto/mvnw micro-user-profile/mvnw \
    && common-dto/mvnw install -DskipTests \
    && micro-user-profile/mvnw package -DskipTests

FROM eclipse-temurin:21-jre-alpine
COPY --from=build micro-user-profile/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]