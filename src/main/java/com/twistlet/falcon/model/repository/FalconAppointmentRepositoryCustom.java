package com.twistlet.falcon.model.repository;

import java.util.Date;
import java.util.List;

import com.twistlet.falcon.model.entity.FalconAppointment;

public interface FalconAppointmentRepositoryCustom {
	
	List<FalconAppointment> listFullByAppointmentDateBetween(Date start, Date end);

}
