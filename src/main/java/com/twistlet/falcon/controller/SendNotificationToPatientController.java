package com.twistlet.falcon.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.model.service.StaffService;

@Controller
public class SendNotificationToPatientController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private final StaffService staffService;

	@Autowired
	public SendNotificationToPatientController(final StaffService staffService) {
		this.staffService = staffService;

	}

	@RequestMapping("/send-notification-to-patient")
	@ResponseBody
	public String send(@RequestParam("name") final String name,
			@RequestParam("mail") final String mail,
			@RequestParam("phone") final String phone,
			@RequestParam("message") final String message) {
		logger.debug("sending message {}, to {}/{}/{}", new Object[] { message,
				name, mail, phone });
		if (!StringUtils.isEmpty(mail)) {
			logger.debug("sending email to {}", mail);
			final boolean result = staffService.sendEmail(name, mail, message);
			logger.debug("sent email to {} - ", mail, result);
		}
		return StringUtils.EMPTY;
	}

}