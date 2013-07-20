package com.twistlet.falcon.model.service;

public interface NotificationWelcomeService {

	void send(String fullName, String ic, String hp, String mail, String password, String patronOf);
}
