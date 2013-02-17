package com.twistlet.falcon.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompositeMessageSenderServiceImpl implements
		CompositeMessageSenderService {

	private final List<MessageSenderService> messageSenderServices;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public CompositeMessageSenderServiceImpl(
			final List<MessageSenderService> messageSenderServices) {
		this.messageSenderServices = messageSenderServices;
	}

	@Override
	public void send(final String sendTo, final String message) {
		for (final MessageSenderService messageSenderService : messageSenderServices) {
			try {
				messageSenderService.send(sendTo, message);
			} catch (final Exception e) {
				logger.error("Error sending message to {}", sendTo);
				e.printStackTrace();
			}
		}

	}

}
