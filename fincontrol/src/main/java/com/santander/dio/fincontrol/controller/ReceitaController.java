package com.santander.dio.fincontrol.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.dio.fincontrol.dto.request.ReceitaRequest;
import com.santander.dio.fincontrol.dto.response.ReceitaResponse;
import com.santander.dio.fincontrol.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping
    public ResponseEntity<ReceitaResponse> salvar(
            @RequestBody ReceitaRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(receitaService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReceitaResponse>> listar() {
        List<ReceitaResponse> lista = receitaService.listar();
        return lista.isEmpty()
            ?ResponseEntity.noContent().build()
            :ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaResponse> atualizar(
            @PathVariable Long id, @RequestBody ReceitaRequest dto) {
        return ResponseEntity.ok(receitaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
