package com.twistlet.falcon.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.entity.QFalconRole;
import com.twistlet.falcon.model.entity.QFalconUser;
import com.twistlet.falcon.model.entity.QFalconUserRole;

@Repository
public class FalconUserRepositoryImpl implements FalconUserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<FalconUser> findByRolename(final String rolename) {
		final JPQLQuery query = new JPAQuery(entityManager);
		final QFalconUser falconUser = QFalconUser.falconUser;
		final QFalconRole falconRole = QFalconRole.falconRole;
		final QFalconUserRole falconUserRole = QFalconUserRole.falconUserRole;
		query.from(falconUser);
		query.join(falconUser.falconUserRoles, falconUserRole);
		query.join(falconUserRole.falconRole, falconRole);
		query.where(falconRole.roleName.eq(rolename));
		return query.list(falconUser);
	}
}
