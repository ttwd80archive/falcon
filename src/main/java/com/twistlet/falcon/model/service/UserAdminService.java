package com.twistlet.falcon.model.service;

import com.twistlet.falcon.model.entity.FalconUser;

public interface UserAdminService {

	FalconUser getFalconUser(String username);
	
	void updateAdmin(FalconUser user);
}
