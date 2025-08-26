package com.santander.dio.fincontrol.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santander.dio.fincontrol.dto.request.UsuarioRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Schema(hidden = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    
    @Email
    @Column(unique = true, nullable = false )
    private String email;

    private String telefone;    
   
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval= true)
    @JsonIgnore
    private List<Transacao> transacoes;

    public static Usuario fromRequest(UsuarioRequest dto){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        
        return usuario;     
    }

}

