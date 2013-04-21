package com.twistlet.falcon.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
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

	@Override
	@Transactional(readOnly = true)
	public Set<User> listAvailablePatrons(FalconUser admin, Date start, Date end) {
		List<User> allPatron = listRegisteredPatrons(admin);
		Set<User> availablePatron = new HashSet<>();
		Set<FalconPatron> busyPatrons = falconPatronRepository.findPatronsDateRange(admin, start, end);
		for(User user: allPatron){
			boolean found = false;
			for(FalconPatron patron : busyPatrons){
				if(StringUtils.equals(user.getUsername(), patron.getFalconUserByPatron().getUsername())){
					found = true;
					break;
				}
			}
			if(!found){
				availablePatron.add(user);
			}
		}
		return availablePatron;
	}

	@Override
	@Transactional(readOnly = true)
	public FalconPatron findPatron(String patron, String admin) {
		FalconUser falconPatron = new FalconUser();
		falconPatron.setUsername(patron);
		FalconUser falconAdmin = new FalconUser();
		falconAdmin.setUsername(admin);
		List<FalconPatron> falconPatrons = falconPatronRepository.findByFalconUserByAdminAndFalconUserByPatron(falconAdmin, falconPatron);
		FalconPatron uniqeFalconPatron = DataAccessUtils.uniqueResult(falconPatrons);
		return uniqeFalconPatron;
	}

}
