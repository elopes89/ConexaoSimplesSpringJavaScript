package com.apifront.projeto.service;

import com.apifront.projeto.model.UsuarioModel;
import com.apifront.projeto.repository.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
  private IUsuario repository;

    private PasswordEncoder passwordEncoder;

    public UsuarioService(IUsuario repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UsuarioModel> listarUsuario() {
        List<UsuarioModel> lista = repository.findAll();
        return lista;
    }
    public UsuarioModel criarUsuario(UsuarioModel usuario){
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        return repository.save(usuario);
    }

    public UsuarioModel editarUsuario(UsuarioModel usuario){
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
      return repository.save(usuario);
    }

    public String excluirUsuario(Long id) {
        repository.deleteById(id);
        return "USUÁRIO EXCLUÍDO";
    }

    public Boolean validarSenha(UsuarioModel usuario) {
        String senha = repository.getReferenceById(usuario.getId()).getSenha();
        Boolean valid = passwordEncoder.matches(usuario.getSenha(), senha);
        return valid;
    }

}

