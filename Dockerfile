FROM openjdk:17

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR construido (asegúrate de que se ha construido antes de construir la imagen Docker)
COPY target/diarymedispring-0.0.1-SNAPSHOT.jar app.jar
# Expone el puerto 8080 (el puerto predeterminado para aplicaciones Spring Boot)
EXPOSE 3000 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
