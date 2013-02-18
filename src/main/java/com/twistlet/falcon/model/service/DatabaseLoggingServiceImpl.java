package com.twistlet.falcon.model.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconMessageLog;
import com.twistlet.falcon.model.repository.FalconMessageLogRepository;

@Service
public class DatabaseLoggingServiceImpl implements DatabaseLoggingService {

	private final FalconMessageLogRepository falconMessageLogRepository;

	@Autowired
	public DatabaseLoggingServiceImpl(
			final FalconMessageLogRepository falconMessageLogRepository) {
		this.falconMessageLogRepository = falconMessageLogRepository;
	}

	@Override
	@Transactional
	public void logEmailSent(final String address, final String message,
			final String errorMessage) {
		logMessageSent(address, message, errorMessage, "mail");

	}

	private void logMessageSent(final String address, final String message,
			final String errorMessage, final String messageType) {
		final FalconMessageLog item = new FalconMessageLog();
		item.setDestination(address);
		item.setErrorMessage(errorMessage);
		item.setMessage(message);
		item.setMessageType(messageType);
		item.setSentTime(new Date());
		falconMessageLogRepository.save(item);
	}

}
