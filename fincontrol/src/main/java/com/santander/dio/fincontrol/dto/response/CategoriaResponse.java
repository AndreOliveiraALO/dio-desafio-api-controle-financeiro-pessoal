package com.santander.dio.fincontrol.dto.response;

import com.santander.dio.fincontrol.model.Categoria;
import com.santander.dio.fincontrol.utils.TipoCategoria;

public record CategoriaResponse(Long id, String nome, TipoCategoria tipo) {
    public static CategoriaResponse fromEntity(Categoria categoria){
        return new CategoriaResponse(
            categoria.getId(), categoria.getNome(), categoria.getTipo()
        );
    }
}
