/*package com.santander.dio.fincontrol.exception;

// houve um erro de compatibilidade com a versão do Spring Boot
// tive alterar a versão no pom.xml do spring para 3.3.4 pois o
// 3.5.5 estava dando conflito com o springdoc-openapi ao capturar 
// exceções genéricas. conforme mostra as configuração comentada abaixo.
// <parent>
//     <groupId>org.springframework.boot</groupId>
//     <artifactId>spring-boot-starter-parent</artifactId>
//     <version>3.3.4</version>
//     <relativePath/>
// </parent>
// terminei voltando a versao 3.5.5 para nao perder os recursos mais novos
// e comentando esta class para não haver conflito, e evoluir depois.

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Tratamento específico para recurso não encontrado
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Recurso não encontrado");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // Tratamento genérico para exceções inesperadas 
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        // Evita interferir no SpringDoc/OpenAPI (Swagger) deixando o mesmo tratar os erros.
        //if (ex.getClass().getName().startsWith("org.springdoc")) {
            //throw new RuntimeException(ex);
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        //}

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Erro interno no servidor");
        body.put("message", ex.getMessage() != null ? ex.getMessage() : "Ocorreu um erro inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    } 
}*/