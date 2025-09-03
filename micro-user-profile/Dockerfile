FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiamos los dos módulos que usa este micro
COPY common-dto/ common-dto/
COPY micro-user-profile/ micro-user-profile/

# Ejecuta Maven DENTRO de cada carpeta
RUN chmod +x common-dto/mvnw micro-user-profile/mvnw \
 && cd /app/common-dto && ./mvnw -B -DskipTests install \
 && cd /app/micro-user-profile && ./mvnw -B -DskipTests package

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/micro-user-profile/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
