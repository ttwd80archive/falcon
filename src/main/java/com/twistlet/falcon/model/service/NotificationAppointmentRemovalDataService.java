package com.twistlet.falcon.model.service;

import java.util.List;

import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;

public interface NotificationAppointmentRemovalDataService {

	String generateContentByAppointmentId(Integer appointmentId);

	List<FalconUser> listPatronByAppointmentId(Integer appointmentId);

	FalconStaff getStaffByAppointmentId(Integer appointmentId);

	void sendEmailToStaff(FalconStaff falconStaff, String content);

	void sendEmailToPatrons(List<FalconUser> patrons, String content);
}
