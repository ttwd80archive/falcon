package com.twistlet.falcon.model.service;

import java.util.List;

import javax.mail.internet.MimeMessage;

public interface NotificationAppointmentRemovalService {

	List<MimeMessage> createMessages(Integer appointmentId);

	void send(List<MimeMessage> list);
}
