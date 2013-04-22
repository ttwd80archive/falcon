package com.twistlet.falcon.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.controller.bean.User;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.service.PatronService;
import com.twistlet.falcon.model.service.StaffService;

@Controller
public class ListPatronController {

	private final StaffService staffService;

	private final PatronService patronService;

	@Autowired
	public ListPatronController(StaffService staffService,
			PatronService patronService) {
		this.staffService = staffService;
		this.patronService = patronService;
	}

	@RequestMapping("/list-patient")
	@ResponseBody
	public List<Map<String, String>> listPatient(
			@RequestParam("term") final String partialName) {
		final List<FalconUser> users = staffService.listPatients(partialName);
		final List<Map<String, String>> list = new ArrayList<>();
		for (final FalconUser falconUser : users) {
			final Map<String, String> map = new LinkedHashMap<>();
			map.put("name", falconUser.getName());
			map.put("phone", StringUtils.trimToEmpty(falconUser.getPhone()));
			map.put("mail", StringUtils.trimToEmpty(falconUser.getEmail()));
			list.add(map);
		}
		return list;
	}

	@RequestMapping("/list-patient/{admin}")
	@ResponseBody
	public List<User> listAllPatrons(@PathVariable("admin") String admin) {
		FalconUser falconUser = new FalconUser();
		falconUser.setUsername(admin);
		List<User> patients = patronService.listRegisteredPatrons(falconUser);
		return patients;
	}
	
	@RequestMapping("/list-patient/{admin}/{date}/{startTime}/{endTime}")
	@ResponseBody
	public Set<User> listAvailablePatrons(@PathVariable("admin") String admin,
			@PathVariable(value="date") String date,
			@PathVariable("startTime") String start,
			@PathVariable("endTime") String end) {
		FalconUser falconUser = new FalconUser();
		final SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy HHmm");
		Set<User> patients = new HashSet<>();
		try {
			final Date startDate = sdf.parse(date + " " + start);
			final Date endDate = sdf.parse(date + " " + end);
			falconUser.setUsername(admin);
			patients = patronService.listAvailablePatrons(falconUser, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return patients;
	}
}