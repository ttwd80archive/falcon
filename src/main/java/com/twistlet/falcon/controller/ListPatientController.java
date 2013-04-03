package com.twistlet.falcon.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.controller.bean.User;
import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.service.AppointmentService;
import com.twistlet.falcon.model.service.StaffService;

@Controller
public class ListPatientController {

	private final StaffService staffService;
	
	private final AppointmentService appointmentService;

	
	@Autowired
	public ListPatientController(StaffService staffService,
			AppointmentService appointmentService) {
		this.staffService = staffService;
		this.appointmentService = appointmentService;
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
	public List<User> listPatients(@PathVariable String admin) {
		FalconUser falconUser = new FalconUser();
		falconUser.setUsername(admin);
		List<User> patients = appointmentService.listRegisteredPatrons(falconUser);
		return patients;
	}
}
