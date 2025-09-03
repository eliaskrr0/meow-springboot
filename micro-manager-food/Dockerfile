FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiamos los dos módulos que usa este micro
COPY common-dto/ common-dto/
COPY micro-manager-food/ micro-manager-food/

# Ejecuta Maven DENTRO de cada carpeta
RUN chmod +x common-dto/mvnw micro-manager-food/mvnw \
 && cd /app/common-dto && ./mvnw -B -DskipTests install \
 && cd /app/micro-manager-food && ./mvnw -B -DskipTests package

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/micro-manager-food/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
