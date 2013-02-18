package com.twistlet.falcon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListPatientController {

	@RequestMapping("/list-patient")
	public String[] listPatient() {
		return new String[] {};
	}
}
