package com.twistlet.falcon.model.service;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
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
	private final MailSenderService mailSenderService;
	private final SmsService smsService;
	private final String message;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public ReminderServiceImpl(final MailSenderService mailSenderService, final SmsService smsService,
			final FalconAppointmentRepository falconAppointmentRepository,
			@Value("${mail.content.reminder}") final String messageLocation) {
		this.mailSenderService = mailSenderService;
		this.smsService = smsService;
		this.falconAppointmentRepository = falconAppointmentRepository;
		try {
			message = StringUtils.join(FileUtils.readLines(new ClassPathResource(messageLocation).getFile()), "\n");
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconAppointment> listAppointmentsNeedingReminders(final long seconds) {
		final Date now = new Date();
		final Date minumumDate = DateUtils.addSeconds(now, new Long(seconds).intValue() * -1);
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
		final String subject = "Your scheduled appointment is due soon";
		final String smsFormat = "Appointment due soon! {0} {1} {2} {3} {4} {5}";
		for (final FalconAppointmentPatron falconAppointmentPatron : falconPatrons) {
			final FalconPatron falconPatron = falconAppointmentPatron.getFalconPatron();
			final FalconUser thePatron = falconPatron.getFalconUserByPatron();
			final FalconUser theAdmin = falconPatron.getFalconUserByAdmin();
			final String sender = theAdmin.getUsername();
			final String patron = thePatron.getName();
			final Object[] arguments = { date, time, staff, patron, venue, service };
			final String mailContent = MessageFormat.format(message, arguments);
			final String smsContent = MessageFormat.format(smsFormat, arguments);
			if (thePatron.getSendEmail()) {
				mailSenderService.send(sender, thePatron.getEmail(), mailContent, subject);
			} else {
				logger.info("{}, {} mail not sent. The patron settings is no mail.", falconAppointment.getId(),
						thePatron.getUsername());
			}
			if (thePatron.getSendSms()) {
				smsService.send(sender, thePatron.getPhone(), smsContent);
			} else {
				logger.info("{}, {} sms not sent. The patron settings is no sms.", falconAppointment.getId(),
						thePatron.getUsername());
			}
		}
		falconAppointment.setNotified('Y');
		falconAppointmentRepository.save(falconAppointment);
	}
}
