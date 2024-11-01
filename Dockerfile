# Usa una imagen base con Java y Maven
FROM maven:3.8.5-openjdk-17

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y el código fuente
COPY pom.xml .
COPY src ./src

# Compila el proyecto
RUN mvn clean package

# Comando para ejecutar tu aplicación
CMD ["java", "-jar", "target/tu-aplicacion.jar"]