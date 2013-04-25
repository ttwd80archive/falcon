package com.twistlet.falcon.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	@RequestMapping("/list-location/{admin}/{date}/{startTime}/{endTime}")
	@ResponseBody
	public Set<FalconLocation> listAvailableLocation(@PathVariable String admin,
			@PathVariable(value="date") String date,
			@PathVariable("startTime") String start,
			@PathVariable("endTime") String end) {
		final SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy HHmm");
		FalconUser falconUser = new FalconUser();
		falconUser.setUsername(admin);
		Set<FalconLocation> locations = new HashSet<>();
		try {
			final Date startDate = sdf.parse(date + " " + start);
			final Date endDate = sdf.parse(date + " " + end);
			locations = locationService.listAvailableLocations(falconUser, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return locations;
	}
	
}
