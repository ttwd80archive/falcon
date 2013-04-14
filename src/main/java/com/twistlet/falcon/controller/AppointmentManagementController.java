package com.twistlet.falcon.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.service.AppointmentService;

@Controller
public class AppointmentManagementController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private final AppointmentService appointmentService;
	
	@Autowired
	public AppointmentManagementController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}


	@RequestMapping("/admin/manage-appointments")
	public ModelAndView view(){
		Date now = new Date();
		List<FalconAppointment> falconAppointments = appointmentService.listMonthlySchedule(now);
		ModelAndView mav = new ModelAndView("admin/manage_appointments");
		mav.addObject("appointments", falconAppointments);
		return mav;
	}
}
