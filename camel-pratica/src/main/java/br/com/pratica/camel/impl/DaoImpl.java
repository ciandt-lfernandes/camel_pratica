package br.com.pratica.camel.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.pratica.camel.dao.Dao;
import br.com.pratica.camel.model.Pessoa;
import br.com.pratica.camel.model.ResponseStringObject;

/*
 * Camada responsável pelo acesso ao banco de dados
 */


@Repository 
@Transactional
public class DaoImpl implements Dao {


	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	/* Garante que a mesma EntityManger é usada para todas as transações
	 * @return entityManager usado para as transações de banco de dados
	 */
	public EntityManager getEntityManager() {
		if (entityManager == null){
			entityManagerFactory = Persistence.createEntityManagerFactory("camelPraticaUnit");
			return this.entityManager = entityManagerFactory.createEntityManager();
		}else{
			return this.entityManager;
		}
	}


	/*
	 * @param pessoa - objeto a ser persistido no banco 
	 * @return ResponseStringObject - Um objeto que contém a pessoa inserida e uma mensagem de status
	 */
	public ResponseStringObject persistir(Pessoa pessoa) {

		ResponseStringObject response;

		try {
			System.out.println("persistindo pessoa: " + pessoa.getNome());
			pessoa.setId(null);

			getEntityManager().getTransaction().begin();
			pessoa = getEntityManager().merge(pessoa);
			getEntityManager().getTransaction().commit();

			response = new ResponseStringObject("Pessoa inserida com sucesso.", pessoa);

		} catch (Exception e) {
			e.printStackTrace();
			return  new ResponseStringObject("Erro ao inserir a pessoa.", null);
		}

		return response;
	}


	/*
	 * @param id - id da pessoa no banco  
	 * @return ResponseStringObject - Um objeto que contém a pessoa consultada e uma mensagem de status
	 */

	public ResponseStringObject consultaId(Integer id) {
		Pessoa p;
		ResponseStringObject response;

		try {
			System.out.println("consultado pessoa de id: " + id);

			p = getEntityManager().find(Pessoa.class, id);

			response = new ResponseStringObject("Pessoa localizada com sucesso", p);


		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseStringObject("Erro ao consultar a pessoa.", null);
		}

		return response;
	}

	/*
	 * @param nome - Nome da pessoa no banco  
	 * @return ResponseStringObject - Um objeto que contém a pessoa consultada e uma mensagem de status
	 */
	public ResponseStringObject consultaNome(String nome) {
		Pessoa p = null;
		ResponseStringObject response;

		try {
			System.out.println("consultado pessoa de nome: " + nome);

			Query q = getEntityManager().createQuery("SELECT p FROM Pessoa p WHERE p.nome = :nomePessoa", Pessoa.class);
			q.setParameter("nomePessoa", nome);

			p = (Pessoa) q.getSingleResult();

			response = new ResponseStringObject("Pessoa localizada com sucesso", p);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseStringObject("Erro ao consultar a pessoa.", null);
		}

		return response;
	}


	/*
	 * @param id - id da pessoa a ser deletada do banco  
	 * @return String - Uma mensagem de status 
	 */
	public String deletarPessoa(Integer id) {

		try {
			System.out.println("deletando pessoa com o id: " + id);

			Pessoa p = getEntityManager().find(Pessoa.class, id);

			if (p != null) {
				getEntityManager().getTransaction().begin();
				getEntityManager().flush();
				getEntityManager().remove(p); 
				getEntityManager().getTransaction().commit();
			} else {
				return "Pessoa não encontrada";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao deletar a pessoa";
		}

		return "Pessoa deletada com sucesso";
	}


	/*
	 * @param pessoa - Objeto com os novos valores a serem atualizados no banco  
	 * @return ResponseStringObject - Um objeto que contém a pessoa atualizada e uma mensagem de status
	 */
	public ResponseStringObject editarPessoa(Pessoa p) {

		ResponseStringObject response;

		try {
			System.out.println("atualizando pessoa: " + p.getNome());

			getEntityManager().getTransaction().begin();
			p = getEntityManager().merge(p);
			getEntityManager().flush();
			getEntityManager().getTransaction().commit();

			response = new ResponseStringObject("Pessoa editada com sucesso", p);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseStringObject("Erro ao consultar a pessoa.", null);
		}

		return response;
	}

}
