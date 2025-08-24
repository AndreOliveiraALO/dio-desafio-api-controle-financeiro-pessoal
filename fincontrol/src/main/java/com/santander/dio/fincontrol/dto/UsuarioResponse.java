package com.santander.dio.fincontrol.dto;

import com.santander.dio.fincontrol.model.Usuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String telefone
) {
    public static UsuarioResponse fromEntity(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone()
        );
    }
}
