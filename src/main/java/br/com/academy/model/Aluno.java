package br.com.academy.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.academy.enums.Curso;
import br.com.academy.enums.Status;
import br.com.academy.enums.Turno;

@Entity
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "nome")
    @Size(min = 2, max = 35, message= "O nome deve conter pelo menos 2 caracteres!")
    @NotBlank(message = "O nome não pode ser vazio!")
    private String nome;
    
    @Column(name = "curso")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo não pode ser nulo!")
    private Curso curso;
    
    @Column(name = "matricula")
    @NotBlank(message = "A matrícula não pode ser vazia!")
    private String matricula;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo não pode ser nulo!")
    private Status status;
    
    @Column(name = "turno")
    @Enumerated(EnumType.STRING)
    private Turno turno;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public Status getStatus() {
        return status;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
