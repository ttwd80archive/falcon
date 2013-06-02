package com.twistlet.falcon.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.falcon.controller.bean.SearchAppointment;
import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconAppointmentPatron;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.service.AppointmentService;
import com.twistlet.falcon.model.service.PatronService;

@Controller
public class AppointmentManagementController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private final AppointmentService appointmentService;
	
	private final PatronService patronService;
	
	@Autowired
	public AppointmentManagementController(
			AppointmentService appointmentService, PatronService patronService) {
		this.appointmentService = appointmentService;
		this.patronService = patronService;
	}

	@InitBinder
	public void initFalconPatronsBinder(final WebDataBinder dataBinder){
		dataBinder.registerCustomEditor(Set.class, "falconAppointmentPatrons", new CustomCollectionEditor(Set.class){
			 @Override
	         protected Object convertElement(Object element){
				 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			     String adminUsername = auth.getName();
				 String username = StringUtils.EMPTY;
				 if(element instanceof String && !((String)element).equals("")){
					 username = (String) element;
				 }
				 else{
					 return null;
				 }
				 FalconAppointmentPatron falconAppointmentPatron = new FalconAppointmentPatron();
				 FalconPatron falconPatron = patronService.findPatron(username, adminUsername);
				 falconAppointmentPatron.setFalconPatron(falconPatron);
				 return falconAppointmentPatron;
			 }
		});
	}

	@RequestMapping("/admin/manage-appointments")
	public ModelAndView viewAppointments(){
		Date now = new Date();
		List<FalconAppointment> falconAppointments = appointmentService.listMonthlySchedule(now);
		SearchAppointment search = new SearchAppointment();
		ModelAndView mav = new ModelAndView("admin/manage_appointments");
		FalconAppointment appointment = new FalconAppointment();
		mav.addObject("appointment", appointment);
		mav.addObject("appointments", falconAppointments);
		mav.addObject("search", search);
		return mav;
	}
	
	@RequestMapping(value="/admin/search-appointments", method =  RequestMethod.POST)
	public ModelAndView searchAppointments(@ModelAttribute("search") SearchAppointment searchAppointment){
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aaa");
		Date searchDate = null;
		if(StringUtils.isNotBlank(searchAppointment.getSearchDate())){
			String date = searchAppointment.getSearchDate();
			String time = "00:00 am";
			if(StringUtils.isNotBlank(searchAppointment.getSearchTime())){
				time = searchAppointment.getSearchTime();
			}
			try {
				searchDate = sdf.parse(date + " " + time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String patronId = null;
		if(StringUtils.isNotBlank(searchAppointment.getPatronId())){
			patronId = searchAppointment.getPatronId();
		}
		List<FalconAppointment> falconAppointments = appointmentService.findAppointmentsByParameter(searchAppointment.getStaffId(), patronId, searchAppointment.getServiceId(), searchAppointment.getLocationId(), searchDate);
		ModelAndView mav = new ModelAndView("admin/manage_appointments");
		FalconAppointment appointment = new FalconAppointment();
		mav.addObject("appointment", appointment);
		mav.addObject("appointments", falconAppointments);
		mav.addObject("search", searchAppointment);
		return mav;
	}
	
	@RequestMapping("/admin/patron_group_list")
	public ModelAndView viewPatrons(@RequestParam("id") Integer id){
		FalconAppointment appointment = appointmentService.findAppointment(id);
		ModelAndView mav = new ModelAndView("admin/patron_group_list");
		mav.addObject("appointmentPatrons", appointment.getFalconAppointmentPatrons());
		return mav;
	}
	
	@RequestMapping("/admin/delete_appointment/{id}")
	public ModelAndView deleteAppointment(@PathVariable Integer id){
		appointmentService.deleteAppointment(id);
		return new ModelAndView("redirect:/admin/manage-appointments");
	}
	
	@RequestMapping("/admin/delete_patron_appointment/{id}")
	public ModelAndView deletePatronAppointment(@PathVariable Integer id){
		appointmentService.deleteAppointmentPatron(id);
		return new ModelAndView("redirect:/admin/manage-appointments");
	}
	
	
	@RequestMapping("/admin/reschedule_appointment/{id}/{date}/{start}/{end}/{location}")
	public ModelAndView rescheduleAppointment(@PathVariable Integer id, @PathVariable String date, @PathVariable String start, @PathVariable String end, @PathVariable Integer location){	
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aaa");
		try {
			final Date startDate = sdf.parse(date + " " + start);
			final Date endDate = sdf.parse(date + " " + end);
			appointmentService.rescheduleAppointment(id, startDate, endDate, location);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/admin/manage-appointments");
	}
	
	@RequestMapping(value = "/admin/update-appointment", method = RequestMethod.POST)
	public ModelAndView updateAppointmentPatron(@ModelAttribute("appointment") FalconAppointment appointment){
		appointmentService.updateAppointmentPatrons(appointment);
		return new ModelAndView("redirect:/admin/manage-appointments");
	}
	
}
