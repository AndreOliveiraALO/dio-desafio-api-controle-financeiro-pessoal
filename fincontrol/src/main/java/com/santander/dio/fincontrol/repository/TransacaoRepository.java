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

    List<Transacao> findByUsuarioAndDataCriacaoBetween(Usuario usuario, LocalDate inicio, LocalDate fim);

    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t WHERE t.usuario = :usuario AND t.categoria.tipo = 'RECEITA'")
    BigDecimal calcularTotalReceitas(Usuario usuario);

    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transacao t WHERE t.usuario = :usuario AND t.categoria.tipo = 'DESPESA'")
    BigDecimal calcularTotalDespesas(Usuario usuario);

}

