package com.santander.dio.fincontrol.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoRequest(
        String descricao,
        BigDecimal valor,
        LocalDate dataCriacao,
        Long categoriaId,
        Long usuarioId
) {}
