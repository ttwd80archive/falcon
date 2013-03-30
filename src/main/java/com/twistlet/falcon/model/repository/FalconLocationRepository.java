package com.twistlet.falcon.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twistlet.falcon.model.entity.FalconLocation;

public interface FalconLocationRepository extends
		JpaRepository<FalconLocation, Integer> {

}
