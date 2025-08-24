package com.santander.dio.fincontrol.dto.request;

import com.santander.dio.fincontrol.utils.TipoCategoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaRequest(
    @NotBlank String nome,
    @NotNull TipoCategoria tipo
) {}