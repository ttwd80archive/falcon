package com.twistlet.falcon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.model.service.UserAdminService;

@Controller
public class ResetPasswordController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final UserAdminService userAdminService;
	
	@Autowired
	public ResetPasswordController(UserAdminService userAdminService) {
		this.userAdminService = userAdminService;
	}



	@RequestMapping(value = "/verify-identity", method = RequestMethod.POST)
	@ResponseBody
	public String verifyUserNric(@RequestParam("nric") final String nric){
		final boolean success = userAdminService.resetUserPassword(nric);
		if(success == true){
			return "success";
		}
		return "fail";
	}
}
