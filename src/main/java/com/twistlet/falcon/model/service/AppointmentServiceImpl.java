package com.twistlet.falcon.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.repository.FalconAppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	private final FalconAppointmentRepository falconAppointmentRepository;
	
	@Autowired
	public AppointmentServiceImpl(
			FalconAppointmentRepository falconAppointmentRepository) {
		this.falconAppointmentRepository = falconAppointmentRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createAppointment(FalconAppointment falconAppointment) {
		falconAppointmentRepository.save(falconAppointment);
	}

}
