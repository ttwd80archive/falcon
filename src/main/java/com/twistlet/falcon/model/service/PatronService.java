package com.twistlet.falcon.model.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.twistlet.falcon.controller.bean.User;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconUser;

public interface PatronService {

	List<User> listRegisteredPatrons(FalconUser admin);
	
	Set<User> listAvailablePatrons(FalconUser admin, Date start, Date end);
	
	void savePatron(FalconPatron patron);
	
	FalconPatron findPatron(String patron, String admin);

}
