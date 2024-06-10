package br.com.academy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Aluno;

/**
 * Interface para manipulação de operações relacionadas aos alunos no banco de
 * dados.
 */
public interface AlunoDao extends JpaRepository<Aluno, Integer> {

	/**
	 * Busca todos os alunos com status ativo.
	 *
	 * @return lista de alunos com status ativo.
	 */
	@Query("SELECT i FROM Aluno i WHERE i.status = 'ATIVO' ")
	public List<Aluno> findByStatusAtivo();

	/**
	 * Busca todos os alunos com status inativo.
	 *
	 * @return lista de alunos com status inativo.
	 */
	@Query("SELECT i FROM Aluno i WHERE i.status = 'INATIVO' ")
	public List<Aluno> findByStatusInativo();

	/**
	 * Busca todos os alunos com status cancelado.
	 *
	 * @return lista de alunos com status cancelado.
	 */
	@Query("SELECT i FROM Aluno i WHERE i.status = 'CANCELADO' ")
	public List<Aluno> findByStatusCancelado();

	/**
	 * Busca todos os alunos com status trancado.
	 *
	 * @return lista de alunos com status trancado.
	 */
	@Query("SELECT i FROM Aluno i WHERE i.status = 'TRANCADO' ")
	public List<Aluno> findByStatusTrancado();

	/**
	 * Busca alunos pelo nome, ignorando diferenças entre maiúsculas e minúsculas.
	 *
	 * @param nome o nome a ser pesquisado.
	 * @return lista de alunos cujo nome contenha o valor fornecido, ignorando
	 *         diferenças entre maiúsculas e minúsculas.
	 */
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);
}