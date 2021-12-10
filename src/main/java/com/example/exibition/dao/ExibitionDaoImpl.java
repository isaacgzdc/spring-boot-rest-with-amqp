package com.example.exibition.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.exibition.dto.ExibitionDTO;
import com.example.exibition.model.Exibition;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ExibitionDaoImpl implements ExibitionDao {

	@Autowired
	private EntityManager em;

	@Override
	public List<Exibition> findAll(ExibitionDTO dto) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT e ");
		sql.append("FROM Exibition e ");
		if (dto != null) {
			if (dto.fromDate() != null && dto.toDate() != null) {
				sql.append("WHERE e.fromDate >= :fromDate AND e.toDate <= :toDate ");
			}else {
				if(dto.fromDate() != null) {
					sql.append("WHERE e.fromDate >= :fromDate ");
				}
				if(dto.toDate() != null) {
					sql.append("WHERE e.toDate <= :toDate ");
				}
			}
		}
		
		TypedQuery<Exibition> query = em.createQuery(sql.toString(), Exibition.class);

		if (dto != null) {
			if(dto.fromDate() != null && dto.toDate() != null) {
				query.setParameter("fromDate", dto.fromDate());
				query.setParameter("toDate", dto.toDate());
			}			
			if (dto.fromDate() != null) {
				query.setParameter("fromDate", dto.fromDate());
			}
			if (dto.toDate() != null) {
				query.setParameter("toDate", dto.toDate());
			}
		}

		List<Exibition> result = query.getResultList();
		
		result.stream().forEach(e->log.debug(e.toString()));
		
		return result;

	}

}
