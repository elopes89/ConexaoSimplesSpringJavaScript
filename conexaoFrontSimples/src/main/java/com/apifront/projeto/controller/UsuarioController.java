package com.apifront.projeto.controller;

import com.apifront.projeto.model.UsuarioModel;
import com.apifront.projeto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.apifront.projeto.repository.IUsuario;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuario repository;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listaUsuarios() {
        return ResponseEntity.status(200).body(usuarioService.listarUsuario());
    }

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> criarUsuario(@Valid @RequestBody UsuarioModel usuario) {
        UsuarioModel usu = usuarioService.criarUsuario(usuario);
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.CREATED);
    }


    //MÉTODO DO FRONT
    //TESTE API SIMPLES FUNCIONANDO
    @PostMapping
    public String criarUsuarioFront(@Valid @RequestBody UsuarioModel usuario) {
        UsuarioModel usu = new UsuarioModel();
        usu = usuarioService.criarUsuario(usuario);
        return "Usuário cadastrado";
    }

    @PutMapping
    public ResponseEntity<UsuarioModel> editarUsuario(@Valid @RequestBody UsuarioModel usuario) {
        return ResponseEntity.status(200).body(usuarioService.editarUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.status(204).build();
    }

    @Valid
    @PostMapping("/validar")
    public ResponseEntity<UsuarioModel> validarSenha(@RequestBody UsuarioModel usuario) {
        Boolean valid = usuarioService.validarSenha(usuario);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(200).build();
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
