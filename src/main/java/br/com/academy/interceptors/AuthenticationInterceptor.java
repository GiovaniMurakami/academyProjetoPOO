package br.com.academy.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor para autenticação de usuários.
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    /**
     * Intercepta a requisição antes que o controlador seja chamado para verificar
     * se o usuário está autenticado.
     *
     * @param request  a requisição HTTP.
     * @param response a resposta HTTP.
     * @param handler  o handler que está sendo executado.
     * @return true se o usuário estiver autenticado ou se a requisição for para uma
     *         rota pública, false caso contrário.
     * @throws Exception se ocorrer um erro durante o processo de autenticação.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();

        // Allow access to these paths without authentication
        if (uri.equals("/") || uri.equals("/cadastro") || uri.equals("/salvarUsuario") || uri.equals("/login")) {
            return true;
        }

        // Check if the user is authenticated
        if (session.getAttribute("usuarioLogado") != null) {
            return true;
        }

        // Redirect to login page if not authenticated
        response.sendRedirect("/");
        return false;
    }
}