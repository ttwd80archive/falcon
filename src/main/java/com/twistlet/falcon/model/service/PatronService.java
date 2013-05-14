package com.twistlet.falcon.model.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.controller.bean.User;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;

public interface PatronService {

	List<User> listRegisteredPatrons(FalconUser admin);
	
	Set<User> listAvailablePatrons(FalconUser admin, Date start, Date end);
	
	void savePatron(FalconPatron patron);
	
	FalconPatron findPatron(String patron, String admin);
	
	List<FalconPatron> listPatronByAdminNameLike(FalconUser admin, String name);
	
	List<FalconPatron> listPatronByAdminNricLike(FalconUser admin, String nric);
	
	List<FalconPatron> listPatronByAdminEmailLike(FalconUser admin, String nric);
	
	List<FalconPatron> listPatronByAdminMobileLike(FalconUser admin, String mobile);

	List<FalconPatron> listPatronByAdminPatronLike(FalconUser admin, FalconUser patron);

}
