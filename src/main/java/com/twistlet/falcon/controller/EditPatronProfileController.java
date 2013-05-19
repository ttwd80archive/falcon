package com.twistlet.falcon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.service.PatronService;

@Controller
@RequestMapping("/patron")
public class EditPatronProfileController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final PatronService patronService;
	
	
	@Autowired
	public EditPatronProfileController(PatronService patronService) {
		this.patronService = patronService;
	}

	@RequestMapping(value = "/editprofile")
	public ModelAndView displayPatronProfile(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUser = auth.getName();
		FalconPatron falconPatron = patronService.findPatron(loggedInUser);
		ModelAndView mav = new ModelAndView("patron/editprofile");
		mav.addObject("patron", falconPatron);
		return mav;
	}
	
	
	@RequestMapping(value = "/update-patron", method = RequestMethod.POST)
	public ModelAndView createNewPatron(@ModelAttribute("patron") FalconPatron patron) {
		patronService.savePatron(patron);
		return new ModelAndView("redirect:patron_landing");
	}
	
}
