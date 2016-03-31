package vajracode.calocal.server.dao;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

import vajracode.calocal.server.dto.DTO;

public class Dao<T> {
	
	@PersistenceContext protected EntityManager em;	

	public T merge(T o) {
		return em.merge(o);
	}

	public void persist(Object o) {
		em.persist(o);
	}

	public void flush() {
		em.flush();	
	}
	
	public long getId(Object o) {
		return (long) em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(o);
	}
	
	public long getIdDirect(DTO entity) {
	    if (entity instanceof HibernateProxy) {
	        LazyInitializer lazyInitializer = ((HibernateProxy) entity).getHibernateLazyInitializer();
	        if (lazyInitializer.isUninitialized()) {
	            return (Long) lazyInitializer.getIdentifier();
	        }
	    }
	    return entity.getId();
	}
	
	public void delete(Object o) {
		em.remove(o);	
	}
	
	public <P extends DTO> P getReference(Class<P> cls, long id) {
		return id > 0 ? em.getReference(cls, id) : null;
	}
//
//	protected void setLimits(Query q, QueryLimits ql) {
//		if (ql == null || ql.getLimit() <= 0) return;
//		q.setMaxResults(ql.getLimit());
//		q.setFirstResult(ql.getOffset());
//	}

	protected String getEntityName(DTO dto) {
		return getEntityName(dto.getClass());
	}
	
	protected String getEntityName(Class<? extends DTO> cls) {
		String name = cls.getSimpleName();
		int i = name.indexOf("_");
		return i > 0 ? name.substring(0, i) : name;
	}

	protected void setCacheable(Query q) {
		q.setHint("org.hibernate.cacheable", true);
	}
		
	protected <K> TypedQuery<K> createCachedQuery(String region, String qlString, Class<K> resultClass) {
		TypedQuery<K> q = em.createQuery(qlString, resultClass);
		//q.setHint("org.hibernate.cacheable", true);
		//q.setHint("org.hibernate.cacheRegion", region);
		return q;
	}
	
//	protected void setLimits(Query q, QueryLimits ql) {
//		if (ql == null || ql.getLimit() <= 0) return;
//		q.setMaxResults(ql.getLimit());
//		q.setFirstResult(ql.getOffset());
//	}

	public <E> E getSingleResultOrNull(TypedQuery<E> tq) {
		try {
			return tq.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public <P> CriteriaQuery<P> getCriteriaQuery(Class<P> cls) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		return cb.createQuery(cls);
	}	


}
