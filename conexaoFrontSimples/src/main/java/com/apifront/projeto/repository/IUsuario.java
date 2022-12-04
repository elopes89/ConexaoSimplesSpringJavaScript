package com.apifront.projeto.repository;

import com.apifront.projeto.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//BASTANTE PARECIDA COM A JPA, PORÉM EXIGE O CASTING
//import org.springframework.data.repository.CrudRepository;

@EnableJpaRepositories
public interface IUsuario extends JpaRepository<UsuarioModel, Long> {
//
//    @Query(value = "select u from UsuarioModel u where u.nome like %?1%")
//   ArrayList<UsuarioModel> getUserByName(String nome);
    //UsuarioModel findBynomeOrEmail(String nome, String email);
}
