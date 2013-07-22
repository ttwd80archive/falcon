package com.twistlet.falcon.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.falcon.controller.bean.ResetPassword;
import com.twistlet.falcon.model.service.ResetPasswordService;

@Controller
public class ResetPasswordController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final ResetPasswordService resetPasswordService;
	
	@Autowired
	public ResetPasswordController(ResetPasswordService resetPasswordService) {
		this.resetPasswordService = resetPasswordService;
	}

	
	@RequestMapping(value = "/verify-identity", method = RequestMethod.POST)
	@ResponseBody
	public String verifyUserNric(@RequestParam("nric") final String nric){
		final boolean success = resetPasswordService.validateUserIdentity(nric);
		if(success == true){
			return "success";
		}
		return "fail";
	}
	
	@RequestMapping(value = "/verify-identity/reset", method = RequestMethod.GET)
	public ModelAndView verifyRandomUrl(@RequestParam(value="ui") final String random){
		ModelAndView mav = new ModelAndView();
		boolean isValidRandom = resetPasswordService.validateUrl(random);
		String page = "invalid_action";
		if(isValidRandom == true){
			page = "reset_password";
			ResetPassword resetPassword = new ResetPassword();
			resetPassword.setRandom(random);
			mav.addObject("resetPassword",resetPassword);
		}
		mav.setViewName(page);
		return mav;
	}

	@RequestMapping(value = "/verify-identity/reset-password", method = RequestMethod.POST)
	public ModelAndView resetPassword(@ModelAttribute("resetPassword") ResetPassword resetPassword){
		boolean success = false;
		if(StringUtils.equals(resetPassword.getPassword(), resetPassword.getConfirmPassword())){
			success = resetPasswordService.resetPassword(resetPassword.getNric(), resetPassword.getRandom(), resetPassword.getPassword());
		}
		String page = "redirect:../index";
		ModelAndView mav = new ModelAndView();
		if(success != true){
			page = "reset_password";
			resetPassword = new ResetPassword();
			resetPassword.setRandom(resetPassword.getRandom());
			mav.addObject("resetPassword",resetPassword);
		}
		return new ModelAndView(page);
	}
}
