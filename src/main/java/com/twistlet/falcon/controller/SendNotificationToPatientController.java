package com.twistlet.falcon.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.twistlet.falcon.model.service.UserAdminService;

@Controller
public class SendNotificationToPatientController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private final StaffService staffService;
	private final SecurityService securityService;
	private final UserAdminService userAdminService;

	@Autowired
	public SendNotificationToPatientController(final StaffService staffService, final SecurityService securityService,
			final UserAdminService userAdminService) {
		this.staffService = staffService;
		this.securityService = securityService;
		this.userAdminService = userAdminService;

	}

	@RequestMapping("/admin/notification/list-patrons")
	@ResponseBody
	public List<List<String>> listPatrons() {
		final List<List<String>> list = new ArrayList<>();
		if (securityService.isCurrentUserInRole("ROLE_ADMIN")) {
			final String adminId = securityService.getCurrentUserId();
			final List<FalconUser> users = staffService.listPatronByAdminId(adminId);
			for (final FalconUser falconUser : users) {
				final List<String> line = new ArrayList<>();
				line.add(falconUser.getUsername());
				line.add(falconUser.getName());
				list.add(line);
			}
			return list;
		} else {
			return Collections.emptyList();
		}
	}

	@RequestMapping("/admin/notification/send-to-patient")
	@ResponseBody
	public String send(@RequestParam("username") final String username, @RequestParam("message") final String message,
			@RequestParam(value = "sms", required = false) final String sendSms,
			@RequestParam(value = "mail", required = false) final String sendMail) {
		final FalconUser falconUser = userAdminService.getFalconUser(username);
		if (falconUser != null) {
			final String name = falconUser.getName();
			final String mail = falconUser.getEmail();
			final String phone = sanitizePhone(falconUser.getPhone());
			logger.debug("sending message {}, to {}/{}/{}", new Object[] { message, name, mail, phone });
			final String currentUserId = securityService.getCurrentUserId();
			if (!StringUtils.isEmpty(sendMail)) {
				logger.debug("sending email to {}", mail);
				final boolean result = staffService.sendEmail(currentUserId, name, mail, message);
				logger.debug("sent email to {} - {}", mail, result);
			}
			if (!StringUtils.isEmpty(sendSms)) {
				logger.debug("sending sms to {}", phone);
				final boolean result = staffService.sendSms(currentUserId, phone, message);
				logger.debug("sent sms to {} - {}", phone, result);
			}
		}
		return StringUtils.EMPTY;
	}

	private String sanitizePhone(final String phone) {
		if (phone == null) {
			return StringUtils.EMPTY;
		}
		if (phone.startsWith("60")) {
			return phone;
		}
		if (phone.startsWith("01")) {
			return "6" + phone;
		}
		if (phone.startsWith("+")) {
			return phone.substring(1);
		}
		return phone;
	}

}
