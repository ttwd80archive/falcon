package com.twistlet.falcon.model.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.QFalconAppointment;
import com.twistlet.falcon.model.entity.QFalconLocation;
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

}
