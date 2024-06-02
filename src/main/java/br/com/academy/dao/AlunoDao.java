package br.com.academy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Aluno;

public interface AlunoDao extends JpaRepository<Aluno, Integer> {

	@Query("SELECT i FROM Aluno i WHERE i.status = 'ATIVO' ")
	public List<Aluno> findByStatusAtivo();
	
	@Query("SELECT i FROM Aluno i WHERE i.status = 'INATIVO' ")
	public List<Aluno> findByStatusInativo();
	
	@Query("SELECT i FROM Aluno i WHERE i.status = 'CANCELADO' ")
	public List<Aluno> findByStatusCancelado();
	
	@Query("SELECT i FROM Aluno i WHERE i.status = 'TRANCADO' ")
	public List<Aluno> findByStatusTrancado();
	
	
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);
}
