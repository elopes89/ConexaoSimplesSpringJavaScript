package com.apifront.projeto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    @Size(min = 3, message = "O nome deve ter no mínimo três caracteres!")
    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "senha",columnDefinition = "TEXT", length = 200)
    private String senha;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "telefone", length = 16)
    private String telefone;

}
