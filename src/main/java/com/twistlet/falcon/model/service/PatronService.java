package com.twistlet.falcon.model.service;

import java.util.List;

import com.twistlet.falcon.controller.bean.User;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconUser;

public interface PatronService {

	List<User> listRegisteredPatrons(FalconUser admin);
	
	void savePatron(FalconPatron patron);

}
