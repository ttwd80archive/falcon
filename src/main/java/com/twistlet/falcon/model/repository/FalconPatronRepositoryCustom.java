package com.twistlet.falcon.model.repository;

import java.util.Date;
import java.util.Set;

import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconUser;

public interface FalconPatronRepositoryCustom {
	
	Set<FalconPatron> findPatronsDateRange(FalconUser admin, Date start, Date end);

}
