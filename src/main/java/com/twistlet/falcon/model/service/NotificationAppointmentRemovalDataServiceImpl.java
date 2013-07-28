package com.twistlet.falcon.model.service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconAppointmentPatron;
import com.twistlet.falcon.model.entity.FalconLocation;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconService;
import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.repository.FalconAppointmentRepository;

public class NotificationAppointmentRemovalDataServiceImpl implements NotificationAppointmentRemovalDataService {

	private String template;

	private FalconAppointmentRepository falconAppointmentRepository;

	@Override
	public String generateContentByAppointmentId(final Integer appointmentId) {
		final FalconAppointment falconAppointment = falconAppointmentRepository.findOne(appointmentId);
		final SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		final SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss aa");
		final Date dateTime = falconAppointment.getAppointmentDate();
		final FalconStaff falconStaff = falconAppointment.getFalconStaff();
		final FalconLocation falconLocation = falconAppointment.getFalconLocation();
		final FalconService falconService = falconAppointment.getFalconService();
		final Set<FalconAppointmentPatron> falconPatrons = falconAppointment.getFalconAppointmentPatrons();
		final String date = formatDate.format(dateTime);
		final String time = formatTime.format(dateTime);
		final String staff = falconStaff.getName();
		final String venue = falconLocation.getName();
		final String service = falconService.getName();
		final String patron;
		if (falconPatrons.size() == 1) {
			final List<FalconAppointmentPatron> list = new ArrayList<>(falconPatrons);
			final FalconAppointmentPatron item = list.get(0);
			final FalconPatron falconPatron = item.getFalconPatron();
			final FalconUser user = falconPatron.getFalconUserByPatron();
			patron = user.getName();
		} else {
			patron = "(Group of " + falconPatrons.size() + ")";
		}
		final Object[] arguments = { date, time, staff, patron, venue, service };
		String content = MessageFormat.format(template, arguments);
		content = content.replace("\r\n", "\n");
		content = content.replace("\n", "\r\n");
		return content;
	}

	@Override
	public List<FalconUser> listPatronByAppointmentId(final Integer appointmentId) {
		final List<FalconUser> list = new ArrayList<>();
		final FalconAppointment falconAppointment = falconAppointmentRepository.findOne(appointmentId);
		final Set<FalconAppointmentPatron> set = falconAppointment.getFalconAppointmentPatrons();
		for (final FalconAppointmentPatron patron : set) {
			final FalconPatron falconPatron = patron.getFalconPatron();
			final FalconUser user = falconPatron.getFalconUserByPatron();
			list.add(user);
		}
		return list;
	}

	@Override
	public FalconStaff getStaffByAppointmentId(final Integer appointmentId) {
		final FalconAppointment falconAppointment = falconAppointmentRepository.findOne(appointmentId);
		return falconAppointment.getFalconStaff();
	}

	@Override
	public void sendEmailToStaff(final FalconStaff falconStaff, final String content) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendEmailToPatrons(final List<FalconUser> patrons, final String content) {
		// TODO Auto-generated method stub

	}

}
