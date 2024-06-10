package br.com.academy.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.academy.model.Usuario;

/**
 * Interface para manipulação de operações relacionadas aos usuários no banco de
 * dados.
 */
@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo e-mail.
     *
     * @param email o e-mail do usuário a ser buscado.
     * @return o usuário encontrado, ou null se nenhum usuário for encontrado com o
     *         e-mail fornecido.
     */
    Usuario findByEmail(String email);

    /**
     * Busca um usuário pelo e-mail e senha.
     *
     * @param email    o e-mail do usuário.
     * @param password a senha do usuário.
     * @return o usuário encontrado, ou null se nenhum usuário for encontrado com o
     *         e-mail e senha fornecidos.
     */
    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password")
    Usuario findLoginByEmail(String email, String password);
}