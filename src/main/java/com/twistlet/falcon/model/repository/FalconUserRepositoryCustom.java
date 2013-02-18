package com.twistlet.falcon.model.repository;

import java.util.List;

import com.twistlet.falcon.model.entity.FalconUser;

public interface FalconUserRepositoryCustom {
	List<FalconUser> findByRolename(String rolename);
}
