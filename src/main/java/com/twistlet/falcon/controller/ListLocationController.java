package com.twistlet.falcon.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.model.entity.FalconLocation;
import com.twistlet.falcon.model.service.LocationService;

@Controller
public class ListLocationController {

	private final LocationService locationService;

	@Autowired
	public ListLocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@RequestMapping("/list-all-location")
	@ResponseBody
	public List<FalconLocation> listAllLocation(){
		FalconLocation location = new FalconLocation();
		location.setName(StringUtils.EMPTY);
		List<FalconLocation> locations = locationService.findAllLocations();
		locations.add(0, location);
		for(FalconLocation entity : locations){
			entity.setFalconAppointments(null);
		}
		return locations;
	}
	
	
}
