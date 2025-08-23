package com.santander.dio.fincontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.santander.dio.fincontrol.model.Categoria;
import com.santander.dio.fincontrol.repository.CategoriaRepository;
import com.santander.dio.fincontrol.utils.TipoCategoria;


@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository; 

    private CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    } 

    public Optional<Categoria> buscarPorId(Long id){
        return categoriaRepository.findById(id);
    }

    public List<Categoria> buscarPorTipo(TipoCategoria tipo){
        return categoriaRepository.findByTipo(tipo);
    }

    public Optional<Categoria> atualizar(Long id, Categoria categoriaAtualizada){
        return categoriaRepository.findById(id).map(c ->{
            c.setNome(categoriaAtualizada.getNome());
            c.setTipo(categoriaAtualizada.getTipo());
            return categoriaRepository.save(c);
        });
    }

    public void deletar(Long id){
        if(!categoriaRepository.existsById(id))
            throw new RuntimeException("Categoria não encontrada, com id "+id );
        categoriaRepository.deleteById(id);
    }
}
