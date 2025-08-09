# Información sobre el servicio
- Se encarga de gestionar los alimentos, con la posibilidad de agregar nuevos alimentos, actualizarlos, eliminarnos y visualizarlos.
- Se comunica con micro-manager-recipe para poder crear una receta con los alimentos.

# Configuración del entorno
- Java 21 de Eclipse Temurin.
- PostgreSQL versión 17.4.
- Usamos Docker para levantar los servicios de BBDD y ejecutar la aplicación en local. Usar el comando ````docker compose -f "COPIAR_RUTA_COMPLETA\docker-compose.yml" up -d```` sobre la carpeta en la que queramos guardar el contenedor.
- application.properties configurado y capado para trabajar únicamente con el entorno de ````LOCAL````.
- Para el versionado de BBDD utilizamos la herramienta flyway que ejecuta el último script encontrado en la ruta ````java\resources\db\migration\````
- Tenemos habilitado Swagger para poder documentar la API REST a través de la ruta http://localhost:8080/swagger-ui/index.html (ejecutar el proyecto antes).
- Para los test unitarios usamos las herramientas ````Mockito + JUnit5````