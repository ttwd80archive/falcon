package com.twistlet.falcon.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.repository.FalconAppointmentRepository;
import com.twistlet.falcon.model.repository.FalconPatronRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	private final FalconAppointmentRepository falconAppointmentRepository;
	
	private final FalconPatronRepository falconPatronRepository;
	
	@Autowired
	public AppointmentServiceImpl(
			FalconAppointmentRepository falconAppointmentRepository,
			FalconPatronRepository falconPatronRepository) {
		this.falconAppointmentRepository = falconAppointmentRepository;
		this.falconPatronRepository = falconPatronRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createAppointment(FalconAppointment falconAppointment) {
		falconAppointmentRepository.save(falconAppointment);
		for(FalconPatron falconPatron : falconAppointment.getFalconPatrons()){
			falconPatron.setFalconAppointment(falconAppointment);
			falconPatronRepository.save(falconPatron);
		}
	}

	

}
