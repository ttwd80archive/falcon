package com.twistlet.falcon.model.service;

import java.text.MessageFormat;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class NotificationWelcomeServiceImpl implements NotificationWelcomeService {

	private final SimpleMailMessage mailMessage;
	private final MailSender mailSender;
	private final String contentTemplate;

	public NotificationWelcomeServiceImpl(final SimpleMailMessage mailMessage, final MailSender mailSender,
			final String contentTemplate) {
		this.mailMessage = mailMessage;
		this.mailSender = mailSender;
		this.contentTemplate = contentTemplate;
	}

	@Override
	public void send(final String fullName, final String ic, final String hp, final String mail, final String password,
			final String patronOf) {
		final SimpleMailMessage mailMessage = new SimpleMailMessage(this.mailMessage);
		final Object[] arguments = { fullName, ic, hp, mail, password, patronOf };
		final String content = MessageFormat.format(contentTemplate, arguments);
		final String to = MessageFormat.format("\"{0}\" <{1}>", new Object[] { fullName, mail });
		try {
			mailMessage.setTo(to);
			mailMessage.setText(content);
			mailSender.send(mailMessage);
		} catch (final RuntimeException e) {
			e.printStackTrace();
		}
	}
}
