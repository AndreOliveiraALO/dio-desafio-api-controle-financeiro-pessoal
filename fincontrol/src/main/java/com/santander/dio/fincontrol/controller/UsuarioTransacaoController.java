package com.santander.dio.fincontrol.controller;

import com.santander.dio.fincontrol.model.Usuario;
import com.santander.dio.fincontrol.service.TransacaoService;
import com.santander.dio.fincontrol.service.UsuarioService;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.santander.dio.fincontrol.dto.response.TransacaoResponse;

@RestController
@RequestMapping("/usuarios/{usuarioId}/transacoes")
public class UsuarioTransacaoController {

    private final TransacaoService transacaoService;
    private final UsuarioService usuarioService;

    public UsuarioTransacaoController(TransacaoService transacaoService,
                                      UsuarioService usuarioService) {
        this.transacaoService = transacaoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<TransacaoResponse> listar(@PathVariable Long usuarioId,
                                          @RequestParam(required = false) LocalDate inicio,
                                          @RequestParam(required = false) LocalDate fim) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
                                         
        if (inicio != null && fim != null) {
            return transacaoService.listarPorUsuarioEPeriodo(usuario, inicio, fim);
        }
        return transacaoService.listarPorUsuario(usuario);
    }

    @GetMapping("/totais")
    public Map<String, BigDecimal> totais(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("receitas", transacaoService.calcularTotalReceitas(usuario));
        result.put("despesas", transacaoService.calcularTotalDespesas(usuario));
        result.put("saldo", transacaoService.calcularSaldo(usuario));
        return result;
    }
}

