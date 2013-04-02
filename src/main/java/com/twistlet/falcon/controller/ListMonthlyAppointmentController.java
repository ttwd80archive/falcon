package com.twistlet.falcon.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.controller.bean.Schedule;
import com.twistlet.falcon.model.service.AppointmentService;

@Controller
public class ListMonthlyAppointmentController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final AppointmentService appointmentService;
	
	@Autowired
	public ListMonthlyAppointmentController(
			AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@RequestMapping("/list-appointment/{date}")
	@ResponseBody
	public List<Schedule> listAppointmentst(@PathVariable String date) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<Schedule> schedules = null;
		try {
			schedules = appointmentService.getMonthlySchedule(sdf.parse(date));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return schedules;
	}

}
