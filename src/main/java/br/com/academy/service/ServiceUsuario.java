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

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioDao usuarioRepositorio;

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

    public Usuario loginUser(String email, String senha) throws ServiceExp {
        Usuario userLogin = usuarioRepositorio.findLoginByEmail(email, senha);
        if (userLogin == null) {
            throw new ServiceExp("Email não encontrado ou senha inválida");
        }
        return userLogin;
    }
}