package com.twistlet.falcon.model.service;

public interface DatabaseLoggingService {
	void logEmailSent(String address, String message, String errorMessage);
}
