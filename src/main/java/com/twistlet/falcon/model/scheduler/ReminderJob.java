package com.twistlet.falcon.model.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ReminderJob extends QuartzJobBean {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		logger.info("Starting up...");
	}

}
