package vajracode.calocal.server.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.*;

import org.springframework.stereotype.Component;

import vajracode.calocal.server.dto.MealDTO;
import vajracode.calocal.server.dto.UserDTO;

@Component
public class MealDao extends Dao<MealDTO> {
	
	public MealDTO getById(long id) {
		return em.find(MealDTO.class, id);
	}

	public List<MealDTO> getList(Date fromDate, Date toDate, Date fromTime, Date toTime, 
			UserDTO user, int offset, int limit) {		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MealDTO> query = cb.createQuery(MealDTO.class);
		Root<MealDTO> root = query.from(MealDTO.class);
		query.where(getListExpression(cb, root, fromDate, toDate, fromTime, toTime, user));
		query.orderBy(cb.desc(root.get("consumed")));
		return em.createQuery(query)		
			.setFirstResult(offset)
			.setMaxResults(limit)
			.getResultList();
	}	

	public long getListSize(Date fromDate, Date toDate, Date fromTime, Date toTime, UserDTO user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();		
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<MealDTO> root = query.from(MealDTO.class);
		query.select(cb.count(root));
		query.where(getListExpression(cb, root, fromDate, toDate, fromTime, toTime, user));
		return em.createQuery(query).getSingleResult();
	}

	private Predicate getListExpression(CriteriaBuilder cb, Root<MealDTO> root, 
			Date fromDate, Date toDate, Date fromTime, Date toTime, UserDTO user) {		
		Predicate p = cb.equal(root.get("user"), user);
		if (fromDate != null)
			p = cb.and(p, cb.greaterThanOrEqualTo(root.get("consumed"), fromDate));
		if (toDate != null)
			p = cb.and(p, cb.lessThanOrEqualTo(root.get("consumed"), toDate));
		if (fromTime != null)
			p = cb.and(p, cb.greaterThanOrEqualTo(cb.function("time", Date.class, root.get("consumed")), fromTime));
		if (toTime != null)
			p = cb.and(p, cb.lessThanOrEqualTo(cb.function("time", Date.class, root.get("consumed")), toTime));
		return p;
	}

}
