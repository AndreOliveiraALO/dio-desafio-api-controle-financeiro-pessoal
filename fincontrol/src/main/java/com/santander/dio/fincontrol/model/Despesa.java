package com.santander.dio.fincontrol.model;

import java.time.LocalDate;
import com.santander.dio.fincontrol.dto.DespesaRequest;
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

public class Despesa extends Transacao {
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;

    public static Despesa fromRequest(DespesaRequest dto, Usuario usuario, Categoria categoria) {
        Despesa despesa = new Despesa();
        despesa.setDescricao(dto.descricao());
        despesa.setValor(dto.valor());
        despesa.setDataCriacao(dto.dataCriacao() != null ? dto.dataCriacao() : LocalDate.now());
        despesa.setUsuario(usuario);
        despesa.setCategoria(categoria);
        despesa.setDataVencimento(dto.dataVencimento());
        despesa.setDataPagamento(dto.dataPagamento());
        return despesa;
    }    
}
