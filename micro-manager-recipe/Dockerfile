FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiamos los dos módulos que usa este micro
COPY common-dto/ common-dto/
COPY micro-manager-recipe/ micro-manager-recipe/

# Ejecuta Maven DENTRO de cada carpeta
RUN chmod +x common-dto/mvnw micro-manager-recipe/mvnw \
 && cd /app/common-dto && ./mvnw -B -DskipTests install \
 && cd /app/micro-manager-recipe && ./mvnw -B -DskipTests package

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/micro-manager-recipe/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
