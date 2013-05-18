package com.twistlet.falcon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.falcon.model.entity.FalconLocation;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconService;
import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.service.LocationService;
import com.twistlet.falcon.model.service.PatronService;
import com.twistlet.falcon.model.service.ServicesTypeService;
import com.twistlet.falcon.model.service.StaffService;

@Controller
public class AdminManagementController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private final StaffService staffService;
	
	private final PatronService patronService;
	
	private final LocationService locationService;
	
	private final ServicesTypeService serviceTypeService;
	
	@Autowired
	public AdminManagementController(StaffService staffService,
			PatronService patronService, LocationService locationService,
			ServicesTypeService serviceTypeService) {
		this.staffService = staffService;
		this.patronService = patronService;
		this.locationService = locationService;
		this.serviceTypeService = serviceTypeService;
	}



	@RequestMapping("/admin/manageusers")
	public ModelAndView view(){
		FalconStaff falconStaff = new FalconStaff();
		falconStaff.setValid(true);
		FalconPatron falconPatron = new FalconPatron();
		FalconLocation falconLocation = new FalconLocation();
		FalconService falconService = new FalconService();
		ModelAndView mav = new  ModelAndView("admin/admin_management");
		mav.addObject("staff", falconStaff);
		mav.addObject("patron", falconPatron);
		mav.addObject("venue", falconLocation);
		mav.addObject("service", falconService);
		return mav;
	}

	@RequestMapping(value = "/admin/create-staff", method = RequestMethod.POST)
	public ModelAndView createNewStaff(@ModelAttribute("staff") FalconStaff staff) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    FalconUser admin = new FalconUser();
	    admin.setUsername(name);
	    staff.setFalconUser(admin);
	    staffService.saveStaff(staff);
	    return new ModelAndView("redirect:manageusers?f=1");
	}

	@RequestMapping(value = "/admin/create-patron", method = RequestMethod.POST)
	public ModelAndView createNewPatron(@ModelAttribute("patron") FalconPatron patron) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    FalconUser admin = new FalconUser();
	    admin.setUsername(name);
	    patron.setFalconUserByAdmin(admin);
		patronService.savePatron(patron);
		return new ModelAndView("redirect:manageusers?f=2");
	}
	
	@RequestMapping(value = "/admin/create-venue", method = RequestMethod.POST)
	public ModelAndView createNewLocation(@ModelAttribute("venue") FalconLocation location) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    FalconUser admin = new FalconUser();
	    admin.setUsername(name);
	    location.setFalconUser(admin);
	    locationService.saveLocation(location);
	    return new ModelAndView("redirect:manageusers?f=3");
	}
	
	
	@RequestMapping(value = "/admin/create-service", method = RequestMethod.POST)
	public ModelAndView createNewService(@ModelAttribute("service") FalconService service) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    FalconUser admin = new FalconUser();
	    admin.setUsername(name);
	    service.setFalconUser(admin);
	    serviceTypeService.saveService(service);
	    return new ModelAndView("redirect:manageusers?f=4");
	}
	
	@RequestMapping(value = "/admin/delete-staff/{id}", method = RequestMethod.GET)
	public ModelAndView deleteStaff(@PathVariable("id") Integer id) {
		FalconStaff staff = new FalconStaff();
		staff.setId(id);
		staffService.deleteStaff(staff);
		return new ModelAndView("redirect:manageusers?f=1");
	}
	
	@RequestMapping(value = "/admin/delete-patron/{username}/{admin}", method = RequestMethod.GET)
	public ModelAndView deletePatron(@PathVariable("username") String username, @PathVariable("admin") String admin) {
		FalconPatron patron = new FalconPatron();
		FalconUser falconAdmin = new FalconUser();
		falconAdmin.setUsername(admin);
		FalconUser falconPatron = new FalconUser();
		falconPatron.setUsername(username);
		patron.setFalconUserByAdmin(falconAdmin);
		patron.setFalconUserByPatron(falconPatron);
		patronService.deletePatron(patron);
		return new ModelAndView("redirect:manageusers?f=1");
	}
	
}
