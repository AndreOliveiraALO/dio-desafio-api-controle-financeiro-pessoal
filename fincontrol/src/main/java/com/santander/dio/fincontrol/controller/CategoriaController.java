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

import com.santander.dio.fincontrol.dto.CategoriaRequest;
import com.santander.dio.fincontrol.dto.CategoriaResponse;
import com.santander.dio.fincontrol.model.Categoria;
import com.santander.dio.fincontrol.service.CategoriaService;
import com.santander.dio.fincontrol.utils.TipoCategoria;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    } 

    @PostMapping
    public ResponseEntity<CategoriaResponse> salvar(@RequestBody @Valid CategoriaRequest dto){
        Categoria categoria = Categoria.fromRequest(dto);
        Categoria salva = categoriaService.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponse.fromEntity(salva));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listar(){
        List<CategoriaResponse> lista = categoriaService.listar().stream()
            .map(CategoriaResponse::fromEntity)
            .toList();

        return lista.isEmpty() 
            ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(lista);    
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(CategoriaResponse.fromEntity(categoria));             
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CategoriaResponse>> buscarPorTipo(@PathVariable TipoCategoria tipo) {
        List<CategoriaResponse> lista = categoriaService.buscarPorTipo(tipo).stream()
            .map(CategoriaResponse :: fromEntity)
            .toList();
        return ResponseEntity.ok(lista);            
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizar(
            @PathVariable Long id, @RequestBody @Valid CategoriaRequest dto) {
        Categoria categoria = Categoria.fromRequest(dto);        
        Categoria atualizada = categoriaService.atualizar(id, categoria);
        return ResponseEntity.ok(CategoriaResponse.fromEntity(atualizada));             
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id){
        categoriaService.deletar(id);        
        return ResponseEntity.noContent().build();
    }
}
