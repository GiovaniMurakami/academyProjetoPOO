package br.com.academy.webconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.academy.interceptors.AuthenticationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**")
            .excludePathPatterns("/", "/cadastro", "/salvarUsuario", "/login", "/logout", 
                                  "/resources/**", "/static/**", "/public/**", "/webjars/**", "/css/**", "/js/**", "/images/**");
    }
}