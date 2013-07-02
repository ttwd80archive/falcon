package com.twistlet.falcon.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconAppointmentPatron;
import com.twistlet.falcon.model.entity.FalconLocation;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconService;
import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.repository.FalconAppointmentRepository;

@Service
public class ReminderServiceImpl implements ReminderService {

	private final FalconAppointmentRepository falconAppointmentRepository;
	private final SmsService smsService;

	@Autowired
	public ReminderServiceImpl(final SmsService smsService, final FalconAppointmentRepository falconAppointmentRepository) {
		this.smsService = smsService;
		this.falconAppointmentRepository = falconAppointmentRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconAppointment> listAppointmentsNeedingReminders(final int seconds) {
		final Date now = new Date();
		final Date minumumDate = DateUtils.addSeconds(now, -seconds);
		return falconAppointmentRepository.findByAppointmentDateBetweenAndNotified(minumumDate, now, 'N');
	}

	@Override
	@Transactional
	public void sendNotification(final FalconAppointment falconAppointment) {
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
		for (final FalconAppointmentPatron falconAppointmentPatron : falconPatrons) {
			final FalconPatron falconPatron = falconAppointmentPatron.getFalconPatron();
			final FalconUser thePatron = falconPatron.getFalconUserByPatron();

		}
		falconAppointment.setNotified(new Character('Y'));
		falconAppointmentRepository.save(falconAppointment);
	}
}
