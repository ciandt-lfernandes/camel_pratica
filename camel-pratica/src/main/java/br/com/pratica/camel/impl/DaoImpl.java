package br.com.pratica.camel.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import br.com.pratica.camel.dao.Dao;
import br.com.pratica.camel.model.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository 
@Transactional
public class DaoImpl implements Dao {


	private EntityManagerFactory entityManagerFactory;


	public String persistir(Pessoa pessoa) {
		try {
			System.out.println("persistindo pessoa: " + pessoa.getNome());
			pessoa.setId(null);
			entityManagerFactory = Persistence.createEntityManagerFactory("camelPraticaUnit");
			
			EntityManager em = entityManagerFactory.createEntityManager();
		
			em.getTransaction().begin();
			pessoa = em.merge(pessoa);
			em.getTransaction().commit();
			
			em.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro: " + e.getStackTrace();
		}
		
		return "Pessoa inserida com sucesso.";
	}
	
	public Pessoa consultaId(Integer id) {
		Pessoa p;
		
		try {
			System.out.println("consultado pessoa de id: " + id);
			
			entityManagerFactory = Persistence.createEntityManagerFactory("camelPraticaUnit");
			EntityManager em = entityManagerFactory.createEntityManager();
			
			p = em.find(Pessoa.class, id);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			p = null;
		}
		
		return p;
	}
	
	public Pessoa consultaNome(String nome) {
		Pessoa p = null;
		
		try {
			System.out.println("consultado pessoa de nome: " + nome);
			
			entityManagerFactory = Persistence.createEntityManagerFactory("camelPraticaUnit");
			EntityManager em = entityManagerFactory.createEntityManager();
			
			Query q = em.createQuery("SELECT p FROM Pessoa p WHERE p.nome = :nomePessoa", Pessoa.class);
			q.setParameter("nomePessoa", nome);
			
			p = (Pessoa) q.getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
			p = null;
		}
		
		return p;
	}

	public String deletarPessoa(Integer id) {

		
		try {
			System.out.println("deletando pessoa com o id: " + id);
			
			entityManagerFactory = Persistence.createEntityManagerFactory("camelPraticaUnit");
			EntityManager em = entityManagerFactory.createEntityManager();
			
			Pessoa p = em.find(Pessoa.class, id);
			
			if (p != null) {
				em.getTransaction().begin();
				em.flush();
				em.remove(p); 
				em.getTransaction().commit();
			} else {
				return "Pessoa não encontrada";
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao deletar a pessoa";
		}
		
		return "Pessoa deletada com sucesso";
	}

	public String editarPessoa(Pessoa p) {
		try {
			System.out.println("atualizando pessoa: " + p.getNome());
			
			entityManagerFactory = Persistence.createEntityManagerFactory("camelPraticaUnit");
			EntityManager em = entityManagerFactory.createEntityManager();
			em.getTransaction().begin();
			p = em.merge(p);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro: " + e.getStackTrace();
		}
		
		return "Pessoa inserida com sucesso.";
	}

}
