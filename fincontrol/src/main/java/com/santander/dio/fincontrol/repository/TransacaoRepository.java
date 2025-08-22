package com.santander.dio.fincontrol.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santander.dio.fincontrol.model.Transacao;
import com.santander.dio.fincontrol.model.Usuario;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

    List<Transacao> findByUsuario(Usuario usuario);

    List<Transacao> findByUsuarioAndDataBetween(Usuario usuario, LocalDate inicio, LocalDate fim);

    @Query("SELECT SUM(t.valor) FROM transacao WHERE t.usuario = :usuario AND tipo = 'RECEITA'")
    BigDecimal calcularTotalReceitas(Usuario usuario);

    @Query("SELECT SUM(t.valor) FROM transacao WHERE t.usuario = :usuario AND tipo = 'DESPESA'")
    BigDecimal calcularTotalDespesas(Usuario usuario);

}

