package com.twistlet.falcon.model.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailRegistrationConfirmationSenderServiceImpl implements MailRegistrationConfirmationSenderService {

	private MailSender mailSender;

	@Autowired
	public MailRegistrationConfirmationSenderServiceImpl(@Qualifier("signupMailSender") MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void send(String fullName, String ic, String hp, String mail, String patronOf) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		List<String> lines = new ArrayList<>();
		lines.add("Dear user,");
		lines.add("");
		lines.add("");
		lines.add("Your email address your user id for future sign-ins.");
		lines.add("");
		lines.add("Full Name: {0}");
		lines.add("IC NO: {1}");
		lines.add("HP: {2}");
		lines.add("Patron of {3}");
		lines.add("");
		lines.add("");
		lines.add("Please log in & Schedule Away");
		lines.add("");
		lines.add("Warm Regards");
		lines.add("Apris Solutions");
		lines.add("");
		simpleMessage.setTo(MessageFormat.format("{0} <{1}>", new Object[] { fullName, mail }));
		simpleMessage.setSubject("Butter-Bun Sign-Up");
		String unformattedText = StringUtils.join(lines, "\n");
		Object[] arguments = { fullName, ic, hp, mail, patronOf };
		String text = MessageFormat.format(unformattedText, arguments);
		simpleMessage.setText(text);
		mailSender.send(simpleMessage);

	}
}
