package com.twistlet.falcon.model.service;

import java.util.List;

import com.twistlet.falcon.model.entity.FalconAppointment;

public interface ReminderService {

	List<FalconAppointment> listAppointmentsNeedingReminders(long seconds);

	void sendNotification(FalconAppointment falconAppointment);
}
