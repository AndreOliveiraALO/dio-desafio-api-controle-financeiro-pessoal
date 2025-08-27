package com.santander.dio.fincontrol.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.santander.dio.fincontrol.model.Transacao;

public record TransacaoResponse(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate dataCriacao,
        String categoriaNome,
        String tipoCategoria        
) {
    public static TransacaoResponse fromEntity(Transacao transacao) {
        return new TransacaoResponse(
                transacao.getId(),
                transacao.getDescricao(),
                transacao.getValor(),
                transacao.getDataCriacao(),
                transacao.getCategoria().getNome(),
                transacao.getCategoria().getTipo().toString()
        );
    }
}
