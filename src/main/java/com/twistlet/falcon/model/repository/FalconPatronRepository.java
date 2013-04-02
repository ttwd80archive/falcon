package com.twistlet.falcon.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twistlet.falcon.model.entity.FalconPatron;

public interface FalconPatronRepository extends JpaRepository<FalconPatron, Integer> {

}
