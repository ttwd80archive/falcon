package com.twistlet.falcon.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.controller.bean.User;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.repository.FalconPatronRepository;

@Service
public class PatronServiceImpl implements PatronService {
	
	private final FalconPatronRepository falconPatronRepository;
	
	
	@Autowired
	public PatronServiceImpl(FalconPatronRepository falconPatronRepository) {
		this.falconPatronRepository = falconPatronRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> listRegisteredPatrons(FalconUser admin) {
		List<FalconPatron> falconPatrons = falconPatronRepository.findByFalconUserByAdmin(admin);
		List<User> patrons = new ArrayList<>();
		User user = null;
		for(FalconPatron falconPatron : falconPatrons){
			FalconUser falconUser = falconPatron.getFalconUserByPatron();
			user = new User();
			user.setName(falconUser.getName());
			user.setUsername(falconUser.getUsername());
			patrons.add(user);
		}
		return patrons;
	}

}
