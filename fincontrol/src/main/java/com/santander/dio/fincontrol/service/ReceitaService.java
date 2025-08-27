package com.santander.dio.fincontrol.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santander.dio.fincontrol.dto.request.ReceitaRequest;
import com.santander.dio.fincontrol.dto.response.ReceitaResponse;
import com.santander.dio.fincontrol.exception.RecursoNaoEncontradoException;
import com.santander.dio.fincontrol.model.Categoria;
import com.santander.dio.fincontrol.model.Receita;
import com.santander.dio.fincontrol.model.Usuario;
import com.santander.dio.fincontrol.repository.CategoriaRepository;
import com.santander.dio.fincontrol.repository.TransacaoRepository;
import com.santander.dio.fincontrol.repository.UsuarioRepository;

@Service
public class ReceitaService {

    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public ReceitaService(TransacaoRepository transacaoRepository,
            UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<ReceitaResponse> listar() {
        return transacaoRepository.findAll().stream()
                .filter(t -> t instanceof Receita)
                .map(t -> (Receita) t)
                .map(ReceitaResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ReceitaResponse buscarPorId(Long id) {
        Receita receita = transacaoRepository.findById(id)
                .filter(t -> t instanceof Receita)
                .map(t -> (Receita) t)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Receita não encontrada com id " + id));
        return ReceitaResponse.fromEntity(receita);
    }

    @Transactional
    public ReceitaResponse salvar(ReceitaRequest dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Usuário não encontrado"));
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Categoria não encontrada"));

        Receita receita = Receita.fromRequest(dto, usuario, categoria);
        transacaoRepository.save(receita);

        return ReceitaResponse.fromEntity(receita);
    }

    @Transactional
    public ReceitaResponse atualizar(Long id, ReceitaRequest dto) {
        if (!transacaoRepository.existsById(id))
            throw new RecursoNaoEncontradoException(
                "Receita não encontrada com id " + id);

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Categoria não encontrada"));
        
        Receita receita = Receita.fromRequest(dto, usuario, categoria);
        receita.setId(id);        
        return ReceitaResponse.fromEntity(transacaoRepository.save(receita));
    }

    @Transactional
    public void deletar(Long id) {
        if (!transacaoRepository.existsById(id))
            throw new RecursoNaoEncontradoException(
                "Receita não encontrada com id " + id);
        transacaoRepository.deleteById(id);
    }
}
