package com.santander.dio.fincontrol.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.santander.dio.fincontrol.model.Receita;

public record ReceitaResponse(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate dataCriacao,
        String usuarioNome,
        String categoriaNome,
        LocalDate dataRecebimento
) {
    public static ReceitaResponse fromEntity(Receita receita) {
        return new ReceitaResponse(
                receita.getId(),
                receita.getDescricao(),
                receita.getValor(),
                receita.getDataCriacao(),
                receita.getUsuario().getNome(),
                receita.getCategoria().getNome(),
                receita.getDataRecebimento()
        );
    }
}

