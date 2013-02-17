package com.twistlet.falcon.model.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.mail.MailSendException;

public class CompositeMessageSenderServiceImplTest {

	@Test
	public void testSendNone() {
		final List<MessageSenderService> messageSenderServices = Collections
				.emptyList();
		final CompositeMessageSenderServiceImpl unit = new CompositeMessageSenderServiceImpl(
				messageSenderServices);
		try {
			unit.send("somewhere", "ignore");
		} catch (final Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testSendOneOk() {
		final List<MessageSenderService> messageSenderServices = new ArrayList<>();
		final MessageSenderService messageSenderService1 = EasyMock
				.createStrictMock(MessageSenderService.class);
		messageSenderServices.add(messageSenderService1);
		messageSenderService1.send(EasyMock.anyObject(String.class),
				EasyMock.anyObject(String.class));
		EasyMock.replay(messageSenderService1);
		final CompositeMessageSenderServiceImpl unit = new CompositeMessageSenderServiceImpl(
				messageSenderServices);
		try {
			unit.send("somewhere", "ignore");
		} catch (final Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testSendTwoOk() {
		final List<MessageSenderService> messageSenderServices = new ArrayList<>();
		final MessageSenderService messageSenderService1 = EasyMock
				.createStrictMock(MessageSenderService.class);
		final MessageSenderService messageSenderService2 = EasyMock
				.createStrictMock(MessageSenderService.class);
		messageSenderServices.add(messageSenderService1);
		messageSenderServices.add(messageSenderService2);
		messageSenderService1.send(EasyMock.anyObject(String.class),
				EasyMock.anyObject(String.class));
		messageSenderService2.send(EasyMock.anyObject(String.class),
				EasyMock.anyObject(String.class));
		EasyMock.replay(messageSenderService1, messageSenderService2);
		final CompositeMessageSenderServiceImpl unit = new CompositeMessageSenderServiceImpl(
				messageSenderServices);
		try {
			unit.send("somewhere", "ignore");
		} catch (final Exception e) {
			fail(e.toString());
		}
		EasyMock.verify(messageSenderService1, messageSenderService2);
	}

	@Test
	public void testSendThreeOkBadOk() {
		final List<MessageSenderService> messageSenderServices = new ArrayList<>();
		final MessageSenderService messageSenderService1 = EasyMock
				.createStrictMock(MessageSenderService.class);
		final MessageSenderService messageSenderService2 = EasyMock
				.createStrictMock(MessageSenderService.class);
		final MessageSenderService messageSenderService3 = EasyMock
				.createStrictMock(MessageSenderService.class);
		messageSenderServices.add(messageSenderService1);
		messageSenderServices.add(messageSenderService2);
		messageSenderServices.add(messageSenderService3);
		messageSenderService1.send(EasyMock.anyObject(String.class),
				EasyMock.anyObject(String.class));
		messageSenderService2.send(EasyMock.anyObject(String.class),
				EasyMock.anyObject(String.class));
		EasyMock.expectLastCall().andThrow(
				new MailSendException("unable to send"));
		messageSenderService3.send(EasyMock.anyObject(String.class),
				EasyMock.anyObject(String.class));
		EasyMock.replay(messageSenderService1, messageSenderService2,
				messageSenderService3);
		final CompositeMessageSenderServiceImpl unit = new CompositeMessageSenderServiceImpl(
				messageSenderServices);
		try {
			unit.send("somewhere", "ignore");
		} catch (final Exception e) {
			fail(e.toString());
		}
		EasyMock.verify(messageSenderService1, messageSenderService2,
				messageSenderService3);
	}

}
