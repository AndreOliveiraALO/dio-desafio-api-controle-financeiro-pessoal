package com.santander.dio.fincontrol.model;

import java.time.LocalDate;

import com.santander.dio.fincontrol.dto.request.ReceitaRequest;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receita extends Transacao {    
    
    private LocalDate dataRecebimento;

    public static Receita fromRequest(ReceitaRequest dto, Usuario usuario, Categoria categoria) {
    Receita receita = new Receita();
    receita.setDescricao(dto.descricao());
    receita.setValor(dto.valor());
    receita.setDataCriacao(dto.dataCriacao() != null ? dto.dataCriacao() : LocalDate.now());
    receita.setUsuario(usuario);
    receita.setCategoria(categoria);
    receita.setDataRecebimento(dto.dataRecebimento());
    return receita;
}
}

