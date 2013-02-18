package com.twistlet.falcon.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twistlet.falcon.model.entity.FalconUser;

public interface FalconUserRepository extends
		JpaRepository<FalconUser, String>, FalconUserRepositoryCustom {

}
