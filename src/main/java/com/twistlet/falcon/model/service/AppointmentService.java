package com.twistlet.falcon.model.service;

import java.util.Date;
import java.util.List;

import com.twistlet.falcon.controller.bean.Schedule;
import com.twistlet.falcon.controller.bean.User;
import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconUser;

public interface AppointmentService {

	void createAppointment(FalconAppointment falconAppointment);
	
	List<Schedule> getMonthlySchedule(Date date);
	
	List<User> listRegisteredPatrons(FalconUser admin);
}
