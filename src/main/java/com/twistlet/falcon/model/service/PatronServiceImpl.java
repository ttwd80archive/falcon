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
		boolean newUser = false;
		if(StringUtils.isBlank(user.getUsername())){
			user.setUsername(names[0]);
			user.setValid(true);
			newUser = true; 
		}
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(passwordEncoder.encodePassword(names[0], names[0]));
		}
		patron.setFalconUserByPatron(user);
		falconUserRepository.save(user);
		if(newUser){
			falconPatronRepository.save(patron);
		}
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

	@Override
	@Transactional(readOnly = true)
	public List<FalconPatron> listPatronByAdminNameLike(FalconUser admin, String name) {
		List<FalconPatron> falconPatrons = falconPatronRepository.findByFalconUserNameLike(admin, name);
		List<FalconPatron> validPatrons = new ArrayList<>();
		for(FalconPatron falconPatron : falconPatrons){
			if(StringUtils.equals(falconPatron.getFalconUserByAdmin().getUsername(), admin.getUsername())){
				validPatrons.add(falconPatron);
			}
		}
		return validPatrons;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconPatron> listPatronByAdminNricLike(FalconUser admin, String nric) {
		List<FalconPatron> falconPatrons = falconPatronRepository.findByFalconUserNricLike(admin, nric);
		List<FalconPatron> validPatrons = new ArrayList<>();
		for(FalconPatron falconPatron : falconPatrons){
			if(StringUtils.equals(falconPatron.getFalconUserByAdmin().getUsername(), admin.getUsername())){
				validPatrons.add(falconPatron);
			}
		}
		return validPatrons;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconPatron> listPatronByAdminEmailLike(FalconUser admin, String email) {
		List<FalconPatron> falconPatrons = falconPatronRepository.findByFalconUserEmailLike(admin, email);
		List<FalconPatron> validPatrons = new ArrayList<>();
		for(FalconPatron falconPatron : falconPatrons){
			if(StringUtils.equals(falconPatron.getFalconUserByAdmin().getUsername(), admin.getUsername())){
				validPatrons.add(falconPatron);
			}
		}
		return validPatrons;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconPatron> listPatronByAdminMobileLike(FalconUser admin,
			String mobile) {
		List<FalconPatron> falconPatrons = falconPatronRepository.findByFalconUserHpTelLike(admin, mobile);
		List<FalconPatron> validPatrons = new ArrayList<>();
		for(FalconPatron falconPatron : falconPatrons){
			if(StringUtils.equals(falconPatron.getFalconUserByAdmin().getUsername(), admin.getUsername())){
				validPatrons.add(falconPatron);
			}
		}
		return validPatrons;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconPatron> listPatronByAdminPatronLike(FalconUser admin, FalconUser patron) {
		List<FalconPatron> patrons = falconPatronRepository.findByFalconUserPatronLike(admin, patron);
		for(FalconPatron falconPatron : patrons){
			falconPatron.getFalconUserByPatron().getName();
		}
		return patrons;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deletePatron(FalconPatron patron) {
		List<FalconPatron> patrons = listPatronByAdminPatronLike(patron.getFalconUserByAdmin(), patron.getFalconUserByPatron());
		FalconPatron uniquePatron = null;
		for(FalconPatron record : patrons){
			if(StringUtils.equals(record.getFalconUserByAdmin().getUsername(), patron.getFalconUserByAdmin().getUsername())){
				uniquePatron = record;
				break;
			}
		}
		if(uniquePatron != null){
			falconPatronRepository.delete(uniquePatron);
			falconUserRepository.delete(uniquePatron.getFalconUserByPatron());
		}
	}
	
	
	

}
