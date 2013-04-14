package com.twistlet.falcon.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.controller.bean.Appointment;
import com.twistlet.falcon.controller.bean.Schedule;
import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconAppointmentPatron;
import com.twistlet.falcon.model.service.AppointmentService;

@Controller
public class ListAppointmentController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final AppointmentService appointmentService;
	
	@Autowired
	public ListAppointmentController(
			AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@RequestMapping("/list-appointment/{date}")
	@ResponseBody
	public List<Schedule> listAppointments(@PathVariable String date) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<Schedule> schedules = null;
		try {
			schedules = appointmentService.getMonthlySchedule(sdf.parse(date));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return schedules;
	}
	
	@RequestMapping("/apppointment_fetch")
	@ResponseBody
	public Appointment getAppointments(@RequestParam("id") Integer id) {
		FalconAppointment falconAppointment = appointmentService.findAppointment(id);
		final SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		final SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm aaa");
		Appointment appointment = new Appointment();
		appointment.setId(falconAppointment.getId());
		appointment.setStaff(falconAppointment.getFalconStaff().getName());
		appointment.setLocation(falconAppointment.getFalconLocation().getName());
		appointment.setAppointmentDate(sdfDate.format(falconAppointment.getAppointmentDate()));
		appointment.setAppointmentTime(sdfTime.format(falconAppointment.getAppointmentDate()));
		appointment.setPatrons(new ArrayList<String>());
		for(FalconAppointmentPatron patron : falconAppointment.getFalconAppointmentPatrons()){
			appointment.getPatrons().add(patron.getFalconUser().getName());
		}
		return appointment;
	}

}
