package com.twistlet.falcon.model.service;

import java.util.List;

import com.twistlet.falcon.model.entity.FalconLocation;

public interface LocationService {
	
	List<FalconLocation> findAllLocations();

}
