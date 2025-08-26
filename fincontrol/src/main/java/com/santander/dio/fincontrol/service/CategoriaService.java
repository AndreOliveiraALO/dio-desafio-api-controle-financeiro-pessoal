package com.santander.dio.fincontrol.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santander.dio.fincontrol.dto.request.CategoriaRequest;
import com.santander.dio.fincontrol.dto.response.CategoriaResponse;
import com.santander.dio.fincontrol.exception.RecursoNaoEncontradoException;
import com.santander.dio.fincontrol.model.Categoria;
import com.santander.dio.fincontrol.repository.CategoriaRepository;
import com.santander.dio.fincontrol.utils.TipoCategoria;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository; 

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaResponse salvar(CategoriaRequest dto){
        Categoria categoria = Categoria.fromRequest(dto);
        return CategoriaResponse.fromEntity(
            categoriaRepository.save(categoria));
    }

    public List<CategoriaResponse> listar(){
        return categoriaRepository.findAll().stream()
            .map(CategoriaResponse::fromEntity)
            .toList();
    } 

    public CategoriaResponse buscarPorId(Long id){
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                "Categoria não encontrada, com id "+id));
        return CategoriaResponse.fromEntity(categoria);
    }

    public List<CategoriaResponse> buscarPorTipo(TipoCategoria tipo){
        List<Categoria> categorias = categoriaRepository.findByTipo(tipo);
          
        if (categorias.isEmpty())
            throw new RecursoNaoEncontradoException(
                "Nenhuma categoria encontrada do tipo "+tipo);
        
        return categorias.stream()
            .map(CategoriaResponse::fromEntity)
            .toList();            
    }

    public CategoriaResponse atualizar(Long id, CategoriaRequest dto){
        
        if (!categoriaRepository.existsById(id))
            throw new RecursoNaoEncontradoException(
                "Categoria não encontrada, com id "+id );

        Categoria categoria = Categoria.fromRequest(dto);    
        categoria.setId(id);

        return CategoriaResponse.fromEntity(
            categoriaRepository.save(categoria));
    }

    public void deletar(Long id){
        if(!categoriaRepository.existsById(id))
            throw new RecursoNaoEncontradoException(
                "Categoria não encontrada, com id "+id );
        categoriaRepository.deleteById(id);
    }
}
