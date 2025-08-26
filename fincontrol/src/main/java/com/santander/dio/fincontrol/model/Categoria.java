package com.santander.dio.fincontrol.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santander.dio.fincontrol.dto.request.CategoriaRequest;
import com.santander.dio.fincontrol.utils.TipoCategoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Schema(hidden = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCategoria tipo;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Transacao> transacoes;

    public static Categoria fromRequest(CategoriaRequest dto){
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria.setTipo(dto.tipo());
        return categoria;     
    }
}

