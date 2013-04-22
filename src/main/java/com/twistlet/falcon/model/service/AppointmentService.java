package com.twistlet.falcon.model.service;

import java.util.Date;
import java.util.List;

import com.twistlet.falcon.controller.bean.Schedule;
import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconAppointmentPatron;

public interface AppointmentService {

	void createAppointment(FalconAppointment falconAppointment);
	
	List<Schedule> getMonthlySchedule(Date date);
	
	List<FalconAppointment> listMonthlySchedule(Date date);
	
	FalconAppointment findAppointment(Integer id);
	
	void deleteAppointment(Integer id);
	
	FalconAppointmentPatron findPatron(Integer id);
	
	void deleteAppointmentPatron(Integer id);
	
	void rescheduleAppointment(Integer appointmentId, Date startDate, Date endDate, Integer locationId);
}
