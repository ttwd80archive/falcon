package com.twistlet.falcon.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.entity.QFalconStaff;

@Repository
public class FalconStaffRepositoryImpl implements FalconStaffRepositoryCustom {


	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<FalconStaff> findByFalconUserNameLike(FalconUser admin, String name) {
		final JPQLQuery query = new JPAQuery(entityManager);
		final QFalconStaff falconStaff = QFalconStaff.falconStaff;
		final BooleanExpression conditionNameLike = falconStaff.name.containsIgnoreCase(name);
		final BooleanExpression conditionFalconUser = falconStaff.falconUser.username.eq(admin.getUsername());
		query.from(falconStaff);
		query.where(conditionFalconUser.and(conditionNameLike));
		query.orderBy(falconStaff.name.asc());
		return query.list(falconStaff);
	}

	@Override
	public List<FalconStaff> findByFalconUserNricLike(FalconUser admin, String nric) {
		final JPQLQuery query = new JPAQuery(entityManager);
		final QFalconStaff falconStaff = QFalconStaff.falconStaff;
		final BooleanExpression conditionNricLike = falconStaff.nric.containsIgnoreCase(nric);
		final BooleanExpression conditionFalconUser = falconStaff.falconUser.username.eq(admin.getUsername());
		query.from(falconStaff);
		query.where(conditionFalconUser.and(conditionNricLike));
		query.orderBy(falconStaff.name.asc());
		return query.list(falconStaff);
	}

	@Override
	public List<FalconStaff> findByFalconUserEmailLike(FalconUser admin, String email) {
		final JPQLQuery query = new JPAQuery(entityManager);
		final QFalconStaff falconStaff = QFalconStaff.falconStaff;
		final BooleanExpression conditionNricLike = falconStaff.email.containsIgnoreCase(email);
		final BooleanExpression conditionFalconUser = falconStaff.falconUser.username.eq(admin.getUsername());
		query.from(falconStaff);
		query.where(conditionFalconUser.and(conditionNricLike));
		query.orderBy(falconStaff.name.asc());
		return query.list(falconStaff);
	}

	@Override
	public List<FalconStaff> findByFalconUserStaffLike(FalconUser admin,
			FalconStaff staff) {
		final JPQLQuery query = new JPAQuery(entityManager);
		final QFalconStaff falconStaff = QFalconStaff.falconStaff;
		final BooleanExpression conditionFalconUser = falconStaff.falconUser.username.eq(admin.getUsername());
		query.from(falconStaff);
		BooleanExpression conditionStaff = null;
		if(StringUtils.isNotBlank(staff.getEmail())){
			conditionStaff = falconStaff.email.eq(staff.getEmail());
		}
		if(StringUtils.isNotBlank(staff.getHpTel())){
			if(conditionStaff == null){
				conditionStaff = falconStaff.hpTel.eq(staff.getHpTel());
			}else{
				conditionStaff.and(falconStaff.hpTel.eq(staff.getHpTel()));
			}
		}
		if(StringUtils.isNotBlank(staff.getName())){
			if(conditionStaff == null){
				conditionStaff = falconStaff.name.eq(staff.getName());
			}else{
				conditionStaff.and(falconStaff.name.eq(staff.getName()));
			}
		}
		if(StringUtils.isNotBlank(staff.getNric())){
			if(conditionStaff == null){
				conditionStaff = falconStaff.nric.eq(staff.getNric());
			}else{
				conditionStaff.and(falconStaff.nric.eq(staff.getNric()));
			}
		}
		query.where(conditionFalconUser.and(conditionStaff));
		query.orderBy(falconStaff.name.asc());
		return query.list(falconStaff);
	}

}
