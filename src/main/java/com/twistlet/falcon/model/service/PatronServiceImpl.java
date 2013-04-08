package com.twistlet.falcon.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.controller.bean.User;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.repository.FalconPatronRepository;
import com.twistlet.falcon.model.repository.FalconUserRepository;

@Service
public class PatronServiceImpl implements PatronService {
	
	private final FalconPatronRepository falconPatronRepository;
	
	private final FalconUserRepository falconUserRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public PatronServiceImpl(FalconPatronRepository falconPatronRepository,
			FalconUserRepository falconUserRepository,
			PasswordEncoder passwordEncoder) {
		this.falconPatronRepository = falconPatronRepository;
		this.falconUserRepository = falconUserRepository;
		this.passwordEncoder = passwordEncoder;
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void savePatron(FalconPatron patron) {
		FalconUser user = patron.getFalconUserByPatron();
		String[] names = StringUtils.split(patron.getFalconUserByPatron().getName(), " ");
		user.setUsername(names[0]);
		user.setPassword(passwordEncoder.encodePassword(names[0], names[0]));
		patron.setFalconUserByPatron(user);
		falconUserRepository.save(user);
		falconPatronRepository.save(patron);
	}

}
