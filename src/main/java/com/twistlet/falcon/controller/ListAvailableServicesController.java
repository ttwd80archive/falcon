package com.twistlet.falcon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twistlet.falcon.model.entity.FalconService;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.service.ServicesTypeService;

@Controller
public class ListAvailableServicesController {
	
	private final ServicesTypeService servicesTypeService;

	@Autowired
	public ListAvailableServicesController(
			ServicesTypeService servicesTypeService) {
		this.servicesTypeService = servicesTypeService;
	}
	
	
	@RequestMapping("/list-services/{admin}")
	@ResponseBody
	public List<FalconService> listServices(@PathVariable String admin){
		FalconUser falconUser = new FalconUser();
		falconUser.setUsername(admin);
		List<FalconService> services = servicesTypeService.listAvailableServiceByAdmin(falconUser);
		return services;
	}
	

}
