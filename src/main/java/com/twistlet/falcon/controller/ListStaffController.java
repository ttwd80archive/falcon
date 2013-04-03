package com.twistlet.falcon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.service.StaffService;

@Controller
public class ListStaffController {

	private final StaffService staffService;

	@Autowired
	public ListStaffController(StaffService staffService) {
		this.staffService = staffService;
	}
	
	@RequestMapping("/list-staff/{admin}")
	@ResponseBody
	public List<FalconStaff> listStaffs(@PathVariable String admin) {
		List<FalconStaff> staffs = staffService.listStaffByAdmin(admin);
		for(FalconStaff staff : staffs){
			staff.setFalconUser(null);
			staff.setFalconAppointments(null);
		}
		return staffs;
	}
	
}
