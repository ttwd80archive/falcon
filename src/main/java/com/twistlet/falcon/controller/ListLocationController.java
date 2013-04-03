package com.twistlet.falcon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.model.entity.FalconLocation;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.service.LocationService;

@Controller
public class ListLocationController {

	private final LocationService locationService;

	@Autowired
	public ListLocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@RequestMapping("/list-location/{admin}")
	@ResponseBody
	public List<FalconLocation> listLocation(@PathVariable String admin){
		FalconUser falconUser = new FalconUser();
		falconUser.setUsername(admin);
		List<FalconLocation> locations = locationService.listAdminLocations(falconUser);
		return locations;
	}
	
	
}
