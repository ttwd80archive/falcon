package com.twistlet.falcon.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.controller.bean.User;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.service.StaffService;

@Controller
public class ListStaffController {

	private final StaffService staffService;

	@Autowired
	public ListStaffController(StaffService staffService) {
		this.staffService = staffService;
	}
	
	@RequestMapping("/list-all-staff")
	@ResponseBody
	public List<User> listAllPatient() {
		final List<FalconUser> users = staffService.listAllStaffs();
		final List<User> staffs = new ArrayList<>();
		User staff = new User();
		staff.setName(StringUtils.EMPTY);
		staff.setUsername(StringUtils.EMPTY);
		staffs.add(staff);
		for(FalconUser user : users){
			staff = new User();
			BeanUtils.copyProperties(user, staff);
			staffs.add(staff);
		}
		return staffs;
	}
	
}
