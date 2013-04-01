package com.twistlet.falcon.model.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.repository.FalconUserRepository;

@Service
public class StaffServiceImpl implements StaffService {

	private final FalconUserRepository falconUserRepository;
	private final MailSenderService mailSenderService;
	private final SmsService smsService;

	@Autowired
	public StaffServiceImpl(final FalconUserRepository falconUserRepository,
			final MailSenderService mailSenderService,
			final SmsService smsService) {
		this.falconUserRepository = falconUserRepository;
		this.mailSenderService = mailSenderService;
		this.smsService = smsService;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconUser> listPatients(final String partialName) {
		return falconUserRepository.findByRolenameAndNameLike("ROLE_PATRON",
				partialName);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<FalconUser> listAllPatients() {
		return falconUserRepository.findByRolenameAndNameLike("ROLE_PATRON", StringUtils.EMPTY);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<FalconUser> listAllStaffs() {
		return falconUserRepository.findByRolenameAndNameLike("ROLE_USER", StringUtils.EMPTY);
	}


	@Override
	public boolean sendEmail(final String name, final String address,
			final String message) {
		final String sendTo = "\"" + name + "\"" + " <" + address + ">";
		try {
			mailSenderService.send(sendTo, message);
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sendSms(final String phone, final String message) {
		try {
			smsService.send(phone, message);
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public FalconUser getUser(final String username) {
		return falconUserRepository.findOne(username);
	}
	
}
