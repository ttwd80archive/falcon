package com.twistlet.falcon.model.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.repository.FalconAppointmentRepository;

@Service
public class ReminderServiceImpl implements ReminderService {

	private final FalconAppointmentRepository falconAppointmentRepository;

	@Autowired
	public ReminderServiceImpl(final FalconAppointmentRepository falconAppointmentRepository) {
		this.falconAppointmentRepository = falconAppointmentRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconAppointment> listAppointmentsNeedingReminders(final int seconds) {
		final Date now = new Date();
		final Date minumumDate = DateUtils.addSeconds(now, -seconds);
		return falconAppointmentRepository.findByAppointmentDateBetweenAndNotified(minumumDate, now, "N");
	}

	@Override
	@Transactional
	public void sendNotification(final FalconAppointment falconAppointment) {
		// TODO Auto-generated method stub
		falconAppointment.setNotified(new Character('Y'));
		falconAppointmentRepository.save(falconAppointment);
	}
}
