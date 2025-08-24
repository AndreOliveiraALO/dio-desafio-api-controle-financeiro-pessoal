package com.santander.dio.fincontrol.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.santander.dio.fincontrol.model.Despesa;

public record DespesaResponse(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate dataCriacao,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        String usuarioNome,
        String categoriaNome
) {
    public static DespesaResponse fromEntity(Despesa despesa){
        return new DespesaResponse(
                despesa.getId(),
                despesa.getDescricao(),
                despesa.getValor(),
                despesa.getDataCriacao(),   
                despesa.getDataVencimento(),
                despesa.getDataPagamento(),
                despesa.getUsuario().getNome(),
                despesa.getCategoria().getNome()
        );            
    }
}

