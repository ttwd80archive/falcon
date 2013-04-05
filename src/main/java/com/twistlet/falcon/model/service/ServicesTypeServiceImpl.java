package com.twistlet.falcon.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconService;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.repository.FalconServiceRepository;

@Service
public class ServicesTypeServiceImpl implements ServicesTypeService {

	private final FalconServiceRepository falconServiceRepository;
	
	@Autowired
	public ServicesTypeServiceImpl(
			FalconServiceRepository falconServiceRepository) {
		this.falconServiceRepository = falconServiceRepository;
	}


	@Override
	@Transactional(readOnly = true)
	public List<FalconService> listAvailableServiceByAdmin(FalconUser admin) {
		List<FalconService> services = falconServiceRepository.findByFalconUser(admin);
		for(FalconService service : services){
			service.setFalconUser(null);
			service.setFalconAppointments(null);
		}
		return services;
	}

}
