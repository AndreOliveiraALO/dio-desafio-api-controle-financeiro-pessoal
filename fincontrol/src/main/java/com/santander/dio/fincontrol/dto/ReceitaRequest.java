package com.santander.dio.fincontrol.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReceitaRequest(
        @NotBlank String descricao,
        @NotNull BigDecimal valor,
        LocalDate dataCriacao,
        @NotNull Long usuarioId,
        @NotNull Long categoriaId,
        LocalDate dataRecebimento
) {}
