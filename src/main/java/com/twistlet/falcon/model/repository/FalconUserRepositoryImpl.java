package com.twistlet.falcon.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.entity.QFalconUser;
import com.twistlet.falcon.model.entity.QFalconUserRole;

public class FalconUserRepositoryImpl implements FalconUserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<FalconUser> findByRolename(final String rolename) {
		final JPQLQuery query = new JPAQuery(entityManager);
		final QFalconUser falconUser = QFalconUser.falconUser;
		final QFalconUserRole falconUserRole = QFalconUserRole.falconUserRole;
		query.from(falconUser);
		query.join(falconUser.falconUserRoles, falconUserRole);
		query.where(falconUser.name.eq(rolename));
		return query.list(falconUser);
	}
}
