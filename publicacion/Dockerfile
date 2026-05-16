# Usar imagen base de Eclipse Temurin 21 para Java
FROM eclipse-temurin:21-jdk-jammy

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR al contenedor
COPY presentacion-0.0.1-SNAPSHOT.jar /app/presentacion-0.0.1-SNAPSHOT.jar

# Exponer el puerto
EXPOSE 8081

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "presentacion-0.0.1-SNAPSHOT.jar"]