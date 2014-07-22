package br.com.camel.camel.pojo.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;


import br.com.camel.camel.pojo.dao.Dao;
import br.com.camel.camel.pojo.model.Pessoa;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository 
@Transactional
public class DaoImpl implements Dao {


	private EntityManagerFactory entityManagerFactory;

	@Autowired
	@PersistenceUnit(unitName = "camelPraticaUnit")
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
	    this.entityManagerFactory = entityManagerFactory;
	}

	public void persistir(Pessoa pessoa) {
		try {
			System.out.println("persistindo pessoa: " + pessoa.getNome());
			
			entityManagerFactory = Persistence.createEntityManagerFactory("camelPraticaUnit");
			EntityManager em = entityManagerFactory.createEntityManager();
			em.getTransaction().begin();
			em.flush();
			em.persist(pessoa); 
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			
		
		}
	}

}
