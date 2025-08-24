package com.santander.dio.fincontrol.dto;

public record UsuarioRequest(
        String nome,
        String email,
        String telefone
) {}