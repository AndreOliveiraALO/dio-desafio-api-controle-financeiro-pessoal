package com.santander.dio.fincontrol.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santander.dio.fincontrol.dto.UsuarioRequest;
import com.santander.dio.fincontrol.dto.UsuarioResponse;
import com.santander.dio.fincontrol.exception.RecursoNaoEncontradoException;
import com.santander.dio.fincontrol.model.Usuario;
import com.santander.dio.fincontrol.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponse::fromEntity)
                .toList();
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id " + id));
        return UsuarioResponse.fromEntity(usuario);
    }

    public UsuarioResponse salvar(UsuarioRequest dto) {
        /* Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());*/
        Usuario usuario = Usuario.fromRequest(dto);
        return UsuarioResponse.fromEntity(usuarioRepository.save(usuario));
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest dto) {
        /*usuarioRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id " + id));*/
        if (!usuarioRepository.existsById(id))
            throw new RecursoNaoEncontradoException("Usuário não encontrado com id " + id);
        
        Usuario usuario = Usuario.fromRequest(dto);
        usuario.setId(id);        
        return UsuarioResponse.fromEntity(usuarioRepository.save(usuario));
    }

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id))
            throw new RecursoNaoEncontradoException("Usuário não encontrado com id " + id);
        usuarioRepository.deleteById(id);
    }
}
