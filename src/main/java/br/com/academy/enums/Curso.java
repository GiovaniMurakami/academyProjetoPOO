package br.com.academy.enums;

public enum Curso {
	ADMINISTRACAO("Administracao"),
	INFORMATICA("Informatica"),
	CONTABILIDADE("Contabilidade"),
	ENFERMAGEM("Enfermagem");
	
	private String curso;
	
	private Curso(String curso) {
		this.curso = curso;
	}
}
