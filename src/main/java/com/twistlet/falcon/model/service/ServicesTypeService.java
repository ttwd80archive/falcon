package com.twistlet.falcon.model.service;

import java.util.List;

import com.twistlet.falcon.model.entity.FalconService;
import com.twistlet.falcon.model.entity.FalconUser;

public interface ServicesTypeService {
	
	List<FalconService> listAvailableServiceByAdmin(FalconUser admin);
	
	void saveService(FalconService service);

}
