package br.com.academy.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.exceptions.ServiceExp;
import br.com.academy.model.Aluno;
import br.com.academy.model.Usuario;
import br.com.academy.service.ServiceUsuario;
import br.com.academy.utils.Hash;

/**
 * Controlador para manipular operações relacionadas aos usuários.
 */
@Controller
@RequestMapping("/")
public class UsuarioController {

	@Autowired
	private ServiceUsuario serviceUsuario;

	/**
	 * Método para exibir a página inicial.
	 *
	 * @return ModelAndView contendo a página inicial.
	 */
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("aluno", new Aluno());
		return mv;
	}

	/**
	 * Método para exibir a página de login.
	 *
	 * @return ModelAndView contendo a página de login.
	 */
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		mv.addObject("usuario", new Usuario());
		return mv;
	}

	/**
	 * Método para exibir a página de cadastro de usuário.
	 *
	 * @return ModelAndView contendo a página de cadastro.
	 */
	@GetMapping("/cadastro")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("login/cadastro");
		return mv;
	}

	/**
	 * Método para salvar um novo usuário.
	 *
	 * @param usuario o usuário a ser salvo.
	 * @return ModelAndView redirecionando para a página de login se o cadastro for
	 *         bem-sucedido, caso contrário, exibe a página de cadastro com mensagem
	 *         de erro.
	 * @throws Exception se ocorrer um erro durante o cadastro do usuário.
	 */
	@PostMapping("/salvarUsuario")
	public ModelAndView salvarUsuario(Usuario usuario) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			serviceUsuario.salvarUsuario(usuario);
			mv.setViewName("redirect:/");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("login/cadastro");
		}
		return mv;
	}

	/**
	 * Método para realizar login.
	 *
	 * @param usuario o usuário que está tentando fazer login.
	 * @param br      resultado da validação.
	 * @param session a sessão HTTP.
	 * @return ModelAndView redirecionando para a página inicial se o login for
	 *         bem-sucedido, caso contrário, exibe a página de login com mensagem de
	 *         erro.
	 * @throws NoSuchAlgorithmException se o algoritmo de hash não for encontrado.
	 * @throws ServiceExp               se ocorrer um erro durante o login.
	 */
	@PostMapping("/login")
	public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session)
			throws NoSuchAlgorithmException, ServiceExp {
		ModelAndView mv = new ModelAndView();
		if (br.hasErrors()) {
			mv.setViewName("login/login");
			return mv;
		}

		try {
			Usuario userLogin = serviceUsuario.loginUser(usuario.getEmail(), Hash.md5(usuario.getPassword()));
			if (userLogin == null) {
				mv.addObject("msg", "Usuário não encontrado, tente novamente");
				mv.setViewName("login/login");
			} else {
				session.setAttribute("usuarioLogado", userLogin);
				mv.setViewName("redirect:/index"); // Ensure redirect to a protected route
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("login/login");
		}

		return mv;
	}

	/**
	 * Método para realizar logout.
	 *
	 * @param session a sessão HTTP.
	 * @return ModelAndView redirecionando para a página de login após o logout.
	 */
	@PostMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return login();
	}
}