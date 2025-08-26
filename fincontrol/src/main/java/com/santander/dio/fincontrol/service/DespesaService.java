package com.santander.dio.fincontrol.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santander.dio.fincontrol.dto.request.DespesaRequest;
import com.santander.dio.fincontrol.dto.response.CategoriaResponse;
import com.santander.dio.fincontrol.dto.response.DespesaResponse;
import com.santander.dio.fincontrol.exception.RecursoNaoEncontradoException;
import com.santander.dio.fincontrol.model.Categoria;
import com.santander.dio.fincontrol.model.Despesa;
import com.santander.dio.fincontrol.model.Usuario;
import com.santander.dio.fincontrol.repository.CategoriaRepository;
import com.santander.dio.fincontrol.repository.TransacaoRepository;
import com.santander.dio.fincontrol.repository.UsuarioRepository;
import com.santander.dio.fincontrol.utils.TipoCategoria;

@Service
public class DespesaService {

    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public DespesaService(TransacaoRepository transacaoRepository, UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<DespesaResponse> listar() {
        return transacaoRepository.findAll().stream()
                .filter(t -> t instanceof Despesa)
                .map(t -> (Despesa) t)
                .map(DespesaResponse::fromEntity)                
                .toList();
    }

    public DespesaResponse buscarPorId(Long id) {
        Despesa despesa = transacaoRepository.findById(id)
                .filter(t -> t instanceof Despesa)
                .map(t -> (Despesa) t)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Despesa não encontrada com id " + id));
        return DespesaResponse.fromEntity(despesa);
    }

    public List<CategoriaResponse> buscarPor(TipoCategoria tipo){
        List<Categoria> categorias = categoriaRepository.findByTipo(tipo);
          
        if (categorias.isEmpty())
            throw new RecursoNaoEncontradoException(
                "Nenhuma categoria encontrada do tipo "+tipo);
        
        return categorias.stream()
            .map(CategoriaResponse::fromEntity)
            .toList();            
    }

    public DespesaResponse salvar(DespesaRequest dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Categoria não encontrada"));

        Despesa despesa = Despesa.fromRequest(dto, usuario, categoria);
        transacaoRepository.save(despesa);

        return DespesaResponse.fromEntity(despesa);
    }

    public DespesaResponse atualizar(Long id, DespesaRequest dto) {
        if (!transacaoRepository.existsById(id))
            throw new RecursoNaoEncontradoException(
                "Despesa não encontrado com id " + id);

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Categoria não encontrada"));
        
        Despesa despesa = Despesa.fromRequest(dto, usuario, categoria);
        despesa.setId(id);        
        return DespesaResponse.fromEntity(transacaoRepository.save(despesa));
    }

    public void deletar(Long id) {
        if (!transacaoRepository.existsById(id))
            throw new RecursoNaoEncontradoException(
                "Despesa não encontrado com id " + id);
        transacaoRepository.deleteById(id);
    }
}
