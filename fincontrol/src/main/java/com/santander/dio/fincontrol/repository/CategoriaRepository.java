package com.santander.dio.fincontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santander.dio.fincontrol.model.Categoria;
import com.santander.dio.fincontrol.utils.TipoCategoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByTipo(TipoCategoria tipo);

    List<Categoria> findByNomeContainingIgnoreCase(String nome);

}
