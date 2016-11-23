package com.tbs.service.persistence.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tbs.service.SecurityToken;
import com.tbs.service.persistence.AbstractPersistence;
import com.tbs.service.persistence.Persistible;

@Component
public class PersistenceService extends AbstractPersistence {

	@PersistenceContext(name = "Persistence")
	private EntityManager em;

	@Transactional
	public <T> T persist(T data) {
		setData((Persistible) data);
		return em.merge(data);
	}

	@Transactional
	public Persistible delete(Persistible data) {
		em.remove(data);
		return data;
	}

	@Transactional
	public Persistible saveOrUpdate(Persistible data) {
		setData(data);

		// if(data.stored()){
		return em.merge(data);
		// }else{
		// em.persist(data);
		// return data;
		// }
	}

	@Transactional
	public <T> T findByID(Class<T> entity, Long id) {
		return em.find(entity, id);
	}

	@Transactional
	public <T> List<T> findAll(Class<T> entity) {
		return em.createQuery("Select t from " + entity.getSimpleName() + " t where fechaBorrado is null")
				.getResultList();
	}

	@Transactional
	public <T> List<T> findByQuery(Class<T> entity, String where) {
		return em.createQuery("Select t from " + entity.getSimpleName() + " t where fechaBorrado is null and " + where)
				.getResultList();
	}

	@Transactional
	public <T> List<T> findByNamedQuery(Class<T> entity, String namedQuery, HashMap<String, Object> parameters) {
		Query query = em.createNamedQuery(entity.getSimpleName() + "." + namedQuery);

		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.getResultList();
	}

	// @Transactional
	// public <T> List<T> findByNativeQuery(Class<T> entity, String query){
	// return em.createQuery(query)
	// .getResultList();
	// }

	@Transactional
	public <T> List<T> findAllByName(String name) {
		return em.createQuery("Select t from " + name + " t where fechaBorrado is null").getResultList();
	}

	@Transactional
	public int nativeNull(String name, Long valueId) {
		try {
			return em.createNamedQuery(name).setParameter(1, valueId).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Transactional
	public <T> List<T> findPagination(Class<T> entity, Long from, Long limit) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(entity);

		Root<T> studentRoot = criteriaQuery.from(entity);
		// fechaBorrado is null
		Predicate greaterThanOrEqualTo = builder
				.greaterThanOrEqualTo((Path<Comparable>) studentRoot.<Comparable>get("id"), (Comparable) from);

		criteriaQuery.where(builder.and(greaterThanOrEqualTo, builder.isNull(studentRoot.get("fechaBorrado"))));

		return em.createQuery(criteriaQuery).setMaxResults(limit.intValue()).getResultList();
	}

	@Transactional
	public <T> List<T> findPaginationBy(Class<T> entity, Long from, Long limit, String atribute, Object value) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(entity);

		Root<T> studentRoot = criteriaQuery.from(entity);
		// fechaBorrado is null
		Predicate greaterThanOrEqualTo = builder
				.greaterThanOrEqualTo((Path<Comparable>) studentRoot.<Comparable>get("id"), (Comparable) from);

		criteriaQuery.where(builder.and(greaterThanOrEqualTo, builder.isNull(studentRoot.get("fechaBorrado")),
				builder.equal(studentRoot.get(atribute), value)));

		return em.createQuery(criteriaQuery).setMaxResults(limit.intValue()).getResultList();
	}

	@Transactional
	public <T> List<T> findByEqualAttribute(Class<T> entity, String attributeName, Object attributeValue) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(entity);

		Root<T> studentRoot = criteriaQuery.from(entity);

		criteriaQuery.select(studentRoot);
		criteriaQuery.where(builder.equal(studentRoot.get(attributeName), attributeValue));

		return em.createQuery(criteriaQuery).getResultList();
	}

	@Transactional
	public <T> List<T> findByLikeAttribute(Class<T> entity, String attributeName, Object attributeValue) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(entity);

		Root<T> studentRoot = criteriaQuery.from(entity);

		criteriaQuery.select(studentRoot);
		criteriaQuery.where(builder.like(studentRoot.<String>get(attributeName), "%" + attributeValue + "%"));

		return em.createQuery(criteriaQuery).getResultList();
	}

	@Transactional
	public <T> T login(Class<T> entity, SecurityToken securityToken) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(entity);

		Root<T> studentRoot = criteriaQuery.from(entity);

		criteriaQuery.where(builder.and(builder.equal(studentRoot.get("username"), securityToken.getUser()),
				builder.isNull(studentRoot.get("fechaBorrado")),
				builder.equal(studentRoot.get("password"), securityToken.getCryptedPass())));

		return em.createQuery(criteriaQuery).getSingleResult();
	}
}
