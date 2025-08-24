package com.santander.dio.fincontrol.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.santander.dio.fincontrol.dto.DespesaRequest;
import com.santander.dio.fincontrol.dto.DespesaResponse;
import com.santander.dio.fincontrol.service.DespesaService;

@RestController
@RequestMapping("/api/despesas")
public class DespesaController {

    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @PostMapping
    public ResponseEntity<DespesaResponse> salvar(@RequestBody DespesaRequest dto) {
        return ResponseEntity.ok(despesaService.salvar(dto));        
    }

    @GetMapping
    public ResponseEntity<List<DespesaResponse>> listar() {
        return ResponseEntity.ok(despesaService.listar());

        /*List<DespesaResponse> despesas = despesaService.listar().stream()
                .map(DespesaResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(despesas);*/
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(despesaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaResponse> atualizar(@PathVariable Long id, @RequestBody DespesaRequest request) {
        return ResponseEntity.ok(despesaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        despesaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

