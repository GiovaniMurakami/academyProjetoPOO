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

@Controller
public class AlunoController {

    @Autowired
    private AlunoDao alunoRepositorio;

    @GetMapping("/inserirAlunos")
    public ModelAndView inserirAlunos() {
        ModelAndView mv = new ModelAndView("aluno/formAluno");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

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

    @GetMapping("/alunosAdicionados")
    public ModelAndView listagemAlunos() {
        ModelAndView mv = new ModelAndView("aluno/listAlunos");
        mv.addObject("alunosList", alunoRepositorio.findAll());
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("aluno/alterar");
        Aluno aluno = alunoRepositorio.findById(id).orElse(null);
        mv.addObject("aluno", aluno);
        return mv;
    }

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

    @GetMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable("id") Integer id) {
        alunoRepositorio.deleteById(id);
        return "redirect:/alunosAdicionados";
    }
    
    @GetMapping("filtroAlunos")
    public ModelAndView filtroAlunos() {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("aluno/filtroAlunos");
    	mv.addObject("aluno", new Aluno());
    	return mv;
    }
    
    @GetMapping("/alunosAtivos")
    public ModelAndView listaAlunosAtivos() {
        ModelAndView mv = new ModelAndView("aluno/alunosFiltrados");
        mv.addObject("alunosFiltrados", alunoRepositorio.findByStatusAtivo());
        return mv;
    }
    
    @GetMapping("/alunosCancelados")
    public ModelAndView listaAlunosCancelados() {
        ModelAndView mv = new ModelAndView("aluno/alunosFiltrados");
        mv.addObject("alunosFiltrados", alunoRepositorio.findByStatusCancelado());
        mv.addObject("aluno", new Aluno());
        return mv;
    }
    
    @GetMapping("/alunosInativos")
    public ModelAndView listaAlunosInativos() {
        ModelAndView mv = new ModelAndView("aluno/alunosFiltrados");
        mv.addObject("alunosFiltrados", alunoRepositorio.findByStatusInativo());
        mv.addObject("aluno", new Aluno());
        return mv;
    }
    
    @GetMapping("/alunosTrancados")
    public ModelAndView listaAlunosTrancados() {
        ModelAndView mv = new ModelAndView("aluno/alunosFiltrados");
        mv.addObject("alunosFiltrados", alunoRepositorio.findByStatusTrancado());
        mv.addObject("aluno", new Aluno());
        return mv;
    }
    
    @PostMapping("/pesquisarAluno")
    public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome) {
    	ModelAndView mv = new ModelAndView();
    	List<Aluno> listaAlunos;
    	if(nome == null || nome.trim().isEmpty()) {
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