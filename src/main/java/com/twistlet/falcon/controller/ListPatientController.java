package com.twistlet.falcon.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.controller.bean.User;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.service.StaffService;

@Controller
public class ListPatientController {

	private final StaffService staffService;

	@Autowired
	public ListPatientController(final StaffService staffService) {
		this.staffService = staffService;
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
	
	@RequestMapping("/list-all-patient")
	@ResponseBody
	public List<User> listAllPatient() {
		final List<FalconUser> users = staffService.listAllPatients();
		final List<User> patients = new ArrayList<>();
		User patient = new User();
		patient.setName(StringUtils.EMPTY);
		patient.setUsername(StringUtils.EMPTY);
		patients.add(patient);
		for(FalconUser user : users){
			patient = new User();
			BeanUtils.copyProperties(user, patient);
			patients.add(patient);
		}
		return patients;
	}
}
