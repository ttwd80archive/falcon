package com.twistlet.falcon.model.repository;

import java.util.Date;
import java.util.List;

import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconAppointmentPatron;
import com.twistlet.falcon.model.entity.FalconLocation;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconService;
import com.twistlet.falcon.model.entity.FalconUser;

public interface FalconAppointmentRepositoryCustom {
	
	List<FalconAppointment> listFullByAppointmentDateBetween(Date start, Date end);

	List<FalconAppointment> listAppointmentsByParam(FalconUser staff, FalconPatron patron, Date start, Date end, FalconLocation location, FalconService service);
}
