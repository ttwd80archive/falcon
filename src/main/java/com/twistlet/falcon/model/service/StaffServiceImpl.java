package com.twistlet.falcon.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.repository.FalconUserRepository;

@Service
public class StaffServiceImpl implements StaffService {

	private final FalconUserRepository falconUserRepository;
	private final MailSenderService mailSenderService;

	@Autowired
	public StaffServiceImpl(final FalconUserRepository falconUserRepository,
			final MailSenderService mailSenderService) {
		this.falconUserRepository = falconUserRepository;
		this.mailSenderService = mailSenderService;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconUser> listPatients(final String partialName) {
		return falconUserRepository.findByRolenameAndNameLike("ROLE_PATRON",
				partialName);
	}

	@Override
	public boolean sendEmail(final String name, final String address,
			final String message) {
		final String sendTo = name + " <" + address + ">";
		try {
			mailSenderService.send(sendTo, message);
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
