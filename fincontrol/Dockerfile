# ----------- STAGE 1: BUILD -----------
# Imagem oficial do Maven já com Java 17 
FROM maven:3.9.6-eclipse-temurin-17 AS build
# Define diretório de trabalho
WORKDIR /app
# Copia os arquivos do projeto para dentro do container
COPY . .
# Compila o projeto e gera o .jar em /app/target
RUN mvn clean package -DskipTests
# ----------- STAGE 2: RUNTIME -----------
# Imagem leve do Java para rodar a aplicação
FROM eclipse-temurin:17-jdk-alpine
# Define diretório de trabalho
WORKDIR /app
# Porta usada pela aplicação
EXPOSE 8081
# Copia o .jar gerado do estágio anterior
COPY --from=build /app/target/fincontrol-0.0.1-SNAPSHOT.jar app.jar
# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
