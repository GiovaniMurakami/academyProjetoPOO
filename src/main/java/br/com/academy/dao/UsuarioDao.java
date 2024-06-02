package br.com.academy.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.academy.model.Usuario;

@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password")
    Usuario findLoginByEmail(String email, String password);
}