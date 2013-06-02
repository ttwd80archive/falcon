package com.twistlet.falcon.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.model.entity.FalconFeedback;
import com.twistlet.falcon.model.repository.FalconFeedbackRepository;

@Service
public class CustomerFeedbackServiceImpl implements CustomerFeedbackService {

	private final FalconFeedbackRepository falconFeedbackRepository;
	
	@Autowired	
	public CustomerFeedbackServiceImpl(
			FalconFeedbackRepository falconFeedbackRepository) {
		this.falconFeedbackRepository = falconFeedbackRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void sendFeedback(FalconFeedback feedback) {
		falconFeedbackRepository.save(feedback);
	}

}
