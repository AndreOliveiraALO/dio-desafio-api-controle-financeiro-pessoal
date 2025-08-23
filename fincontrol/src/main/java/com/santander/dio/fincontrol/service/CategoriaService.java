package com.santander.dio.fincontrol.service;

import java.util.List;
import org.springframework.stereotype.Service;

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

    public Categoria salvar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    } 

    public Categoria buscarPorId(Long id){
        return categoriaRepository.findById(id).orElseThrow(
            () -> new RecursoNaoEncontradoException("Categoria não encontrada, com id "+id)
        );
    }

    public List<Categoria> buscarPorTipo(TipoCategoria tipo){
        List<Categoria> categorias = categoriaRepository.findByTipo(tipo);
        if(categorias.isEmpty()){
            throw new RecursoNaoEncontradoException("Nenhuma categoria encontrada do tipo "+tipo);
        }
        return categorias;
    }

    public Categoria atualizar(Long id, Categoria categoriaAtualizada){
        return categoriaRepository.findById(id).map(c ->{
            c.setNome(categoriaAtualizada.getNome());
            c.setTipo(categoriaAtualizada.getTipo());
            return categoriaRepository.save(c);
        }).orElseThrow(() -> new RecursoNaoEncontradoException(
            "Categoria não encontrada, com id "+id )
        );
    }

    public void deletar(Long id){
        if(!categoriaRepository.existsById(id))
            throw new RecursoNaoEncontradoException("Categoria não encontrada, com id "+id );
        categoriaRepository.deleteById(id);
    }
}
