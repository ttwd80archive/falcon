package com.twistlet.falcon.model.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@RunWith(MockitoJUnitRunner.class)
public class NotificationWelcomeServiceImplTest {

	NotificationWelcomeService unit;

	@Mock
	MailSender mailSender;

	@Before
	public void init() throws IOException {

		final SimpleMailMessage mailMessage = new SimpleMailMessage();
		final Charset encoding = Charset.forName("US-ASCII");
		final File contentFile = new ClassPathResource("com/twistlet/falcon/mail/welcome.txt").getFile();
		final String contentTemplate = FileUtils.readFileToString(contentFile, encoding);
		// final String contentTemplate = "{0}, {1}, {2}, {3}";
		unit = new NotificationWelcomeServiceImpl(mailMessage, mailSender, contentTemplate);
	}

	@Test
	public void testSend() {
		final ArgumentCaptor<SimpleMailMessage> argument = ArgumentCaptor.forClass(SimpleMailMessage.class);

		unit.send("Name", "100", "200", "me@me.com", "abc123", "Shankar's Dental Clinic");
		verify(mailSender).send(argument.capture());
		final SimpleMailMessage mailMessage = argument.getValue();
		assertTrue(!mailMessage.getText().contains("{0}"));

	}

}
