package com.santander.dio.fincontrol.dto.request;

public record UsuarioRequest(
        String nome,
        String email,
        String telefone
) {}