package com.tbs.service.persistence.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import com.tbs.service.persistence.AbstractPersistence;
import com.tbs.service.persistence.Persistible;
import com.tbs.service.persistence.PersistibleImpl;

public class PersistenceService  extends AbstractPersistence{
//	private static final EntityManagerFactory emf;
	private static final EntityManager em;
	
	static {
		em = Persistence.createEntityManagerFactory("Persistence").createEntityManager();
//		emf = Persistence.createEntityManagerFactory("Persistence");
//		em = emf.createEntityManager();
		// private EntityTransaction trx = null;
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public Persistible save(Persistible data) {
		em.getTransaction().begin();
		setData(data);
		em.persist(data);
		em.getTransaction().commit();
		return data;
	}

	// private void init() {
	// emf = Persistence.createEntityManagerFactory("Persistence");
	// em = emf.createEntityManager();
	// this.trx = em.getTransaction();
	// }

}
