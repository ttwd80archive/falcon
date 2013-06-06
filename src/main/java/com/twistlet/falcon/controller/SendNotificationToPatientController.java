package com.twistlet.falcon.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.service.SecurityService;
import com.twistlet.falcon.model.service.StaffService;

@Controller
public class SendNotificationToPatientController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private final StaffService staffService;
	private SecurityService securityService;

	@Autowired
	public SendNotificationToPatientController(final StaffService staffService, SecurityService securityService) {
		this.staffService = staffService;
		this.securityService = securityService;

	}

	@RequestMapping("/notification/list-patrons")
	@ResponseBody
	public Map<String, String> listPatrons() {
		if (securityService.isCurrentUserInRole("ROLE_ADMIN")) {
			String adminId = securityService.getCurrentUserId();
			List<FalconUser> list = staffService.listPatronByAdminId(adminId);
			Map<String, String> map = new LinkedHashMap<>();
			for (FalconUser falconUser : list) {
				map.put(falconUser.getUsername(), falconUser.getName());
			}
			return map;
		} else {
			return Collections.emptyMap();
		}
	}

	@RequestMapping("/notification/send-to-patient")
	@ResponseBody
	public String send(@RequestParam("name") final String name, @RequestParam("mail") final String mail,
			@RequestParam("phone") final String phone, @RequestParam("message") final String message) {
		logger.debug("sending message {}, to {}/{}/{}", new Object[] { message, name, mail, phone });
		if (!StringUtils.isEmpty(mail)) {
			logger.debug("sending email to {}", mail);
			final boolean result = staffService.sendEmail(name, mail, message);
			logger.debug("sent email to {} - {}", mail, result);
		}
		if (!StringUtils.isEmpty(phone)) {
			logger.debug("sending sms to {}", phone);
			final boolean result = staffService.sendSms(phone, message);
			logger.debug("sent sms to {} - {}", phone, result);
		}
		return StringUtils.EMPTY;
	}

}
