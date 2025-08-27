package com.santander.dio.fincontrol.service;

import com.santander.dio.fincontrol.model.Usuario;
import com.santander.dio.fincontrol.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.santander.dio.fincontrol.dto.response.TransacaoResponse;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;    

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;        
    }

    @Transactional(readOnly = true)
    public List<TransacaoResponse> listarPorUsuario(Usuario usuario) {
        return transacaoRepository.findByUsuario(usuario)
                .stream()
                .map(TransacaoResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TransacaoResponse> listarPorUsuarioEPeriodo(Usuario usuario, 
            LocalDate inicio, LocalDate fim) {        
        return transacaoRepository.findByUsuarioAndDataCriacaoBetween(usuario, inicio, fim)
                .stream()
                .map(TransacaoResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public BigDecimal calcularTotalReceitas(Usuario usuario) {
        return transacaoRepository.calcularTotalReceitas(usuario);
    }

    @Transactional(readOnly = true)
    public BigDecimal calcularTotalDespesas(Usuario usuario) {
        return transacaoRepository.calcularTotalDespesas(usuario);
    }

    @Transactional(readOnly = true)
    public BigDecimal calcularSaldo(Usuario usuario) {        
        return calcularTotalReceitas(usuario).subtract(calcularTotalDespesas(usuario));
    }
}
