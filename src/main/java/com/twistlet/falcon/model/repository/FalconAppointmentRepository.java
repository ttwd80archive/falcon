package com.twistlet.falcon.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twistlet.falcon.model.entity.FalconAppointment;

public interface FalconAppointmentRepository extends
		JpaRepository<FalconAppointment, Integer> {

}
