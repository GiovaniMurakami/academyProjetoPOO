package br.com.academy.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.dao.UsuarioDao;
import br.com.academy.exceptions.CriptoExistException;
import br.com.academy.exceptions.EmailExistsException;
import br.com.academy.exceptions.ServiceExp;
import br.com.academy.model.Usuario;
import br.com.academy.utils.Hash;

/**
 * Serviço para manipulação de operações relacionadas aos usuários.
 */
@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioDao usuarioRepositorio;

    /**
     * Salva um novo usuário.
     *
     * @param usuario o usuário a ser salvo.
     * @throws Exception se ocorrer um erro durante o processo de salvamento.
     */
    public void salvarUsuario(Usuario usuario) throws Exception {
        if (usuarioRepositorio.findByEmail(usuario.getEmail()) != null) {
            throw new EmailExistsException("Já existe um email cadastrado para: " + usuario.getEmail());
        }

        try {
            usuario.setPassword(Hash.md5(usuario.getPassword()));
        } catch (NoSuchAlgorithmException error) {
            throw new CriptoExistException("Erro na criptografia da senha");
        }

        usuarioRepositorio.save(usuario);
    }

    /**
     * Realiza o login do usuário.
     *
     * @param email o email do usuário.
     * @param senha a senha do usuário.
     * @return o usuário autenticado.
     * @throws ServiceExp se ocorrer um erro durante o processo de login.
     */
    public Usuario loginUser(String email, String senha) throws ServiceExp {
        Usuario userLogin = usuarioRepositorio.findLoginByEmail(email, senha);
        if (userLogin == null) {
            throw new ServiceExp("Email não encontrado ou senha inválida");
        }
        return userLogin;
    }
}