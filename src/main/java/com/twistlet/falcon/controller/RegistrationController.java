package com.twistlet.falcon.controller;

import javax.servlet.http.HttpServletRequest;

import nl.captcha.Captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.falcon.controller.bean.Registration;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.service.PatronService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	private final PatronService patronService;
	
	@Autowired
	public RegistrationController(PatronService patronService) {
		this.patronService = patronService;
	}


	@RequestMapping("/index")
	public ModelAndView index() {
		Registration registration = new Registration();
		ModelAndView mav = new ModelAndView("/registration/index");
		mav.addObject("registration", registration);
		return mav;
	}
	
	
	@RequestMapping(value = "/register-user", method = RequestMethod.POST)
	public ModelAndView createNewUser(final HttpServletRequest req, @ModelAttribute("registration") Registration registration) {
		Captcha captcha = (Captcha) req.getSession().getAttribute( Captcha.NAME );
		String answer = req.getParameter("answer");
		if(captcha.isCorrect(answer)) {
			FalconUser falconUser = new FalconUser();
			falconUser.setEmail(registration.getEmail());
			falconUser.setName(registration.getFullname());
			falconUser.setNric(registration.getNric());
			falconUser.setPassword(registration.getPassword());
			falconUser.setPhone(registration.getPhone());
			FalconUser falconAdmin = new FalconUser();
			falconAdmin.setUsername(registration.getAdmin());
			FalconPatron patron = new FalconPatron();
			patron.setFalconUserByAdmin(falconAdmin);
			patron.setFalconUserByPatron(falconUser);
			patronService.saveNewPatron(patron);
		}else{
			return new ModelAndView("redirect:index");
		}
		return new ModelAndView("redirect:../index");
	}
}
