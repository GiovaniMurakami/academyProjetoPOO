package br.com.academy.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;
import br.com.academy.enums.Turno;

/**
 * Controlador para manipular operações relacionadas aos alunos.
 */
@Controller
public class AlunoController {

    @Autowired
    private AlunoDao alunoRepositorio;

    /**
     * Método para exibir o formulário de inserção de alunos.
     *
     * @return ModelAndView contendo o formulário de inserção de aluno.
     */
    @GetMapping("/inserirAlunos")
    public ModelAndView inserirAlunos() {
        ModelAndView mv = new ModelAndView("aluno/formAluno");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    /**
     * Método para processar a inserção de um novo aluno.
     *
     * @param aluno o aluno a ser inserido.
     * @param br    resultado da validação.
     * @return ModelAndView redirecionando para a página de alunos adicionados se a
     *         inserção for bem-sucedida.
     */
    @PostMapping("/insertAlunos")
    public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()) {
            mv.setViewName("aluno/formAluno");
        } else {
            aluno.setTurno(Turno.fromString(aluno.getTurno().getTurno()));
            alunoRepositorio.save(aluno);
            mv.setViewName("redirect:/alunosAdicionados");
        }
        return mv;
    }

    /**
     * Método para exibir a lista de alunos adicionados.
     *
     * @return ModelAndView contendo a lista de alunos.
     */
    @GetMapping("/alunosAdicionados")
    public ModelAndView listagemAlunos() {
        ModelAndView mv = new ModelAndView("aluno/listAlunos");
        mv.addObject("alunosList", alunoRepositorio.findAll());
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    /**
     * Método para exibir o formulário de alteração de um aluno específico.
     *
     * @param id o ID do aluno a ser alterado.
     * @return ModelAndView contendo o formulário de alteração.
     */
    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("aluno/alterar");
        Aluno aluno = alunoRepositorio.findById(id).orElse(null);
        mv.addObject("aluno", aluno);
        return mv;
    }

    /**
     * Método para processar a alteração de um aluno.
     *
     * @param aluno o aluno a ser alterado.
     * @param br    resultado da validação.
     * @return ModelAndView redirecionando para a página de alunos adicionados se a
     *         alteração for bem-sucedida.
     */
    @PostMapping("/alterar")
    public ModelAndView alterar(@Valid Aluno aluno, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()) {
            mv.setViewName("aluno/alterar");
        } else {
            alunoRepositorio.save(aluno);
            mv.setViewName("redirect:/alunosAdicionados");
        }
        return mv;
    }

    /**
     * Método para excluir um aluno.
     *
     * @param id o ID do aluno a ser excluído.
     * @return String redirecionando para a página de alunos adicionados após a
     *         exclusão.
     */
    @GetMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable("id") Integer id) {
        alunoRepositorio.deleteById(id);
        return "redirect:/alunosAdicionados";
    }

    /**
     * Método para exibir o formulário de filtro de alunos.
     *
     * @return ModelAndView contendo o formulário de filtro.
     */
    @GetMapping("filtroAlunos")
    public ModelAndView filtroAlunos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/filtroAlunos");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    /**
     * Método para exibir a lista de alunos ativos.
     *
     * @return ModelAndView contendo a lista de alunos ativos.
     */
    @GetMapping("/alunosAtivos")
    public ModelAndView listaAlunosAtivos() {
        ModelAndView mv = new ModelAndView("aluno/alunosFiltrados");
        mv.addObject("alunosFiltrados", alunoRepositorio.findByStatusAtivo());
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    /**
     * Método para exibir a lista de alunos cancelados.
     *
     * @return ModelAndView contendo a lista de alunos cancelados.
     */
    @GetMapping("/alunosCancelados")
    public ModelAndView listaAlunosCancelados() {
        ModelAndView mv = new ModelAndView("aluno/alunosFiltrados");
        mv.addObject("alunosFiltrados", alunoRepositorio.findByStatusCancelado());
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    /**
     * Método para exibir a lista de alunos inativos.
     *
     * @return ModelAndView contendo a lista de alunos inativos.
     */
    @GetMapping("/alunosInativos")
    public ModelAndView listaAlunosInativos() {
        ModelAndView mv = new ModelAndView("aluno/alunosFiltrados");
        mv.addObject("alunosFiltrados", alunoRepositorio.findByStatusInativo());
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    /**
     * Método para exibir a lista de alunos trancados.
     *
     * @return ModelAndView contendo a lista de alunos trancados.
     */
    @GetMapping("/alunosTrancados")
    public ModelAndView listaAlunosTrancados() {
        ModelAndView mv = new ModelAndView("aluno/alunosFiltrados");
        mv.addObject("alunosFiltrados", alunoRepositorio.findByStatusTrancado());
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    /**
     * Método para pesquisar alunos por nome.
     *
     * @param nome o nome a ser pesquisado.
     * @return ModelAndView contendo os resultados da pesquisa.
     */
    @PostMapping("/pesquisarAluno")
    public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome) {
        ModelAndView mv = new ModelAndView();
        List<Aluno> listaAlunos;
        if (nome == null || nome.trim().isEmpty()) {
            listaAlunos = alunoRepositorio.findAll();
        } else {
            listaAlunos = alunoRepositorio.findByNomeContainingIgnoreCase(nome);
        }
        mv.addObject("listaAlunos", listaAlunos);
        mv.addObject("aluno", new Aluno());
        mv.setViewName("aluno/pesquisaResultado");
        return mv;
    }
}