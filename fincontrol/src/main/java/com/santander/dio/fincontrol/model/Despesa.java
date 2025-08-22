package com.santander.dio.fincontrol.model;

import java.time.LocalDate;

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
}
