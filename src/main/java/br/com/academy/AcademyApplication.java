package br.com.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal responsável por iniciar a aplicação Spring Boot.
 */
@SpringBootApplication
public class AcademyApplication {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 *
	 * @param args argumentos de linha de comando (não utilizado neste caso).
	 */
	public static void main(String[] args) {
		SpringApplication.run(AcademyApplication.class, args);
	}
}