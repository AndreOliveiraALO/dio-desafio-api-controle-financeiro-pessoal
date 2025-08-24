package com.santander.dio.fincontrol.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DespesaRequest(
        @NotBlank String descricao,
        @NotNull BigDecimal valor,
        LocalDate dataCriacao,
        @NotNull Long usuarioId,
        @NotNull Long categoriaId,
        LocalDate dataVencimento,
        LocalDate dataPagamento
) {}
