package com.twistlet.falcon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppointmentManagementController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/admin/manage-appointments")
	public ModelAndView view(){
		return new ModelAndView("admin/manage_appointments");
	}
}
