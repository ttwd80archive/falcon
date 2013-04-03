package com.twistlet.falcon.model.service;

import java.util.List;

import com.twistlet.falcon.model.entity.FalconLocation;
import com.twistlet.falcon.model.entity.FalconUser;

public interface LocationService {
	
	List<FalconLocation> listAdminLocations(FalconUser admin);

}
