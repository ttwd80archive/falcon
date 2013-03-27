package com.twistlet.falcon.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconLocation;
import com.twistlet.falcon.model.repository.FalconLocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	private final FalconLocationRepository falconLocationRepository;
	
	@Autowired
	public LocationServiceImpl(FalconLocationRepository falconLocationRepository) {
		this.falconLocationRepository = falconLocationRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconLocation> findAllLocations() {
		return falconLocationRepository.findAll();
	}

}
