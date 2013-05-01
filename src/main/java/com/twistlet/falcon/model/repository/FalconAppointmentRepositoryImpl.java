package com.twistlet.falcon.model.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.expr.BooleanExpression;
import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconLocation;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconService;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.entity.QFalconAppointment;
import com.twistlet.falcon.model.entity.QFalconAppointmentPatron;
import com.twistlet.falcon.model.entity.QFalconLocation;
import com.twistlet.falcon.model.entity.QFalconPatron;
import com.twistlet.falcon.model.entity.QFalconService;
import com.twistlet.falcon.model.entity.QFalconStaff;

@Repository
public class FalconAppointmentRepositoryImpl implements
		FalconAppointmentRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<FalconAppointment> listFullByAppointmentDateBetween(Date start,
			Date end) {
		final JPQLQuery query = new JPAQuery(entityManager);
		final QFalconAppointment falconAppointment = QFalconAppointment.falconAppointment;
		final QFalconLocation falconLocation = QFalconLocation.falconLocation;
		final QFalconService falconService = QFalconService.falconService;
		final QFalconStaff falconStaff = QFalconStaff.falconStaff;
		query.from(falconAppointment);
		query.join(falconAppointment.falconStaff, falconStaff);
		query.join(falconAppointment.falconLocation, falconLocation);
		query.join(falconAppointment.falconService, falconService);
		query.orderBy(falconAppointment.appointmentDate.desc());
		query.where(falconAppointment.appointmentDate.between(start, end));
		return query.list(falconAppointment);
	}

	@Override
	public List<FalconAppointment> listAppointmentsByParam(FalconUser staff,
			FalconPatron patron, Date searchDate, FalconLocation location,
			FalconService service) {
		final JPQLQuery query = new JPAQuery(entityManager);
		final QFalconAppointment falconAppointment = QFalconAppointment.falconAppointment;
		final QFalconLocation falconLocation = QFalconLocation.falconLocation;
		final QFalconService falconService = QFalconService.falconService;
		final QFalconStaff falconStaff = QFalconStaff.falconStaff;
		final QFalconAppointmentPatron falconAppointmentPatron = QFalconAppointmentPatron.falconAppointmentPatron;
		final QFalconPatron falconPatron = QFalconPatron.falconPatron;
		query.from(falconAppointment);
		List<BooleanExpression> expressions = new ArrayList<>();
		if (searchDate != null) {
			BooleanExpression t1 = falconAppointment.appointmentDate.loe(searchDate);
			BooleanExpression t2 = falconAppointment.appointmentDateEnd.goe(searchDate);
			BooleanExpression x = t1.and(t2);
			expressions.add(x);
		}
		if (staff != null) {
			query.join(falconAppointment.falconStaff, falconStaff).fetch();
			BooleanExpression x = falconStaff.falconUser.username.eq(staff.getUsername());
			expressions.add(x);
		}
		if (patron != null) {
			query.join(falconAppointment.falconAppointmentPatrons, falconAppointmentPatron).fetch();
			query.join(falconAppointmentPatron.falconPatron, falconPatron).fetch();
			BooleanExpression x = falconPatron.id.eq(patron.getId());
			expressions.add(x);
		}
		if (location != null) {
			query.join(falconAppointment.falconLocation, falconLocation).fetch();
			BooleanExpression x = falconAppointment.falconLocation.id.eq(location.getId());
			expressions.add(x);
		}
		if (service != null) {
			query.join(falconAppointment.falconService, falconService).fetch();
			BooleanExpression x = falconService.id.eq(service.getId());
			expressions.add(x);
		}
		query.where(expressions.toArray(new BooleanExpression[] {}));
		return query.list(falconAppointment);
	}
}
