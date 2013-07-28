package com.twistlet.falcon.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconAppointmentPatron;
import com.twistlet.falcon.model.entity.FalconLocation;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconService;
import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.repository.FalconAppointmentRepository;

public class NotificationAppointmentRemovalServiceImpl implements NotificationAppointmentRemovalService {

	private FalconAppointmentRepository falconAppointmentRepository;
	private String template;
	private JavaMailSender javaMailSender;
	private SimpleMailMessage simpleMailMessage;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional(readOnly = true)
	public List<MimeMessage> createMessages(final Integer appointmentId) {
		final List<MimeMessage> list = new ArrayList<>();
		final FalconAppointment falconAppointment = falconAppointmentRepository.findOne(appointmentId);
		if (falconAppointment != null) {
			final FalconStaff staff = falconAppointment.getFalconStaff();
			final FalconLocation falconLocation = falconAppointment.getFalconLocation();
			final FalconService falconService = falconAppointment.getFalconService();
			final SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			final SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss aa");
			final Date appointmentDate = falconAppointment.getAppointmentDate();
			final String date = formatDate.format(appointmentDate);
			final String time = formatTime.format(appointmentDate);
			final String staffName = staff.getName();
			final Set<FalconAppointmentPatron> set = falconAppointment.getFalconAppointmentPatrons();
			final String patronName = nameOrGroupSize(set);
			final String venue = falconLocation.getName();
			final String service = falconService.getName();
			if (Boolean.TRUE.equals(staff.getSendEmail())) {
				final MimeMessage mimeMessage = createMessage(staffName, staff.getEmail(), date, time, staffName, patronName,
						venue, service);
				list.add(mimeMessage);
			}

			for (final FalconAppointmentPatron item : set) {
			}

		} else {
			logger.warn("Unable to find appointment id # {} for removal messsage creation");
		}

		return list;
	}

	private String nameOrGroupSize(final Set<FalconAppointmentPatron> set) {
		if (set.size() == 1) {
			final FalconAppointmentPatron item = set.toArray(new FalconAppointmentPatron[] {})[0];
			final FalconPatron patron = item.getFalconPatron();
			final FalconUser user = patron.getFalconUserByPatron();
			return user.getName();
		} else {
			return "(Group of " + set.size() + ")";
		}
	}

	private MimeMessage createMessage(final String name, final String email, final String date, final String time,
			final String staffName, final String patronName, final String venue, final String service) {
		final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		final SimpleToMimeConversionService conversionService = new SimpleToMimeConversionService();
		try {
			conversionService.toMime(simpleMailMessage, mimeMessage);
			final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo("\"" + name + "\" <" + email + ">");
		} catch (final MessagingException e) {
			e.printStackTrace();
		}
		return mimeMessage;
	}

	@Override
	public void send(final List<MimeMessage> list) {
		// TODO Auto-generated method stub

	}

}
