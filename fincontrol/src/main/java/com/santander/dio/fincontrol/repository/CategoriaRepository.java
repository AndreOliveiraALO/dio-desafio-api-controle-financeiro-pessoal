package com.santander.dio.fincontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santander.dio.fincontrol.model.Categoria;
import com.santander.dio.fincontrol.model.Usuario;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByUsuario(Usuario usuario);

    List<Categoria> findByNomeContainingIgnoreCase(String nome);

}
