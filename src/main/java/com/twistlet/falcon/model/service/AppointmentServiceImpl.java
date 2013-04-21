package com.twistlet.falcon.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.falcon.controller.bean.Schedule;
import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconAppointmentPatron;
import com.twistlet.falcon.model.repository.FalconAppointmentPatronRepository;
import com.twistlet.falcon.model.repository.FalconAppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	private final FalconAppointmentRepository falconAppointmentRepository;
	
	private final FalconAppointmentPatronRepository falconAppointmentPatronRepository;

	
	@Autowired
	public AppointmentServiceImpl(
			FalconAppointmentRepository falconAppointmentRepository,
			FalconAppointmentPatronRepository falconAppointmentPatronRepository) {
		this.falconAppointmentRepository = falconAppointmentRepository;
		this.falconAppointmentPatronRepository = falconAppointmentPatronRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createAppointment(FalconAppointment falconAppointment) {
		falconAppointmentRepository.save(falconAppointment);
		for(FalconAppointmentPatron falconAppointmentPatron : falconAppointment.getFalconAppointmentPatrons()){
			falconAppointmentPatron.setFalconAppointment(falconAppointment);
			falconAppointmentPatronRepository.save(falconAppointmentPatron);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Schedule> getMonthlySchedule(Date date) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		final Date start = DateUtils.truncate(date, Calendar.MONTH);
		final Date end = DateUtils.addSeconds(DateUtils.ceiling(date, Calendar.MONTH), -1);
		List<FalconAppointment> appointments = falconAppointmentRepository.findByAppointmentDateBetween(start, end);
		HashMap<String, Integer> organiser = new LinkedHashMap<>();
		String key = StringUtils.EMPTY;
		Integer total = 0;
		for(FalconAppointment appointment : appointments){
			key = sdf.format(appointment.getAppointmentDate());
			total = organiser.get(key);
			if(total == null){
				total = 0;
			}
			organiser.put(key, total + 1);
		}
		List<Schedule> schedules = new ArrayList<>();
		Schedule schedule;
		for(String id : organiser.keySet()){
			schedule = new Schedule();
			schedule.setDay(Integer.parseInt(id.substring(6)));
			schedule.setTotalAppointment(organiser.get(id));
			schedules.add(schedule);
		}
		return schedules;	
	}

	@Override
	@Transactional(readOnly = true)
	public List<FalconAppointment> listMonthlySchedule(Date date) {
		final Date start = DateUtils.truncate(date, Calendar.MONTH);
		final Date end = DateUtils.addSeconds(DateUtils.ceiling(date, Calendar.MONTH), -1);
		List<FalconAppointment> appointments = falconAppointmentRepository.listFullByAppointmentDateBetween(start, end);
		for(FalconAppointment appointment : appointments){
			appointment.getFalconStaff().getName();
			appointment.getFalconLocation().getName();
			appointment.getFalconService().getName();
			for(FalconAppointmentPatron falconAppointmentPatron : appointment.getFalconAppointmentPatrons()){
				falconAppointmentPatron.getFalconPatron().getFalconUserByPatron().getName();
			}
		}
		return appointments;
	}

	@Override
	@Transactional(readOnly = true)
	public FalconAppointment findAppointment(Integer id) {
		FalconAppointment falconAppointment = falconAppointmentRepository.findOne(id);
		for(FalconAppointmentPatron falconAppointmentPatron : falconAppointment.getFalconAppointmentPatrons()){
			falconAppointmentPatron.getFalconPatron().getFalconUserByPatron().getName();
			falconAppointmentPatron.getFalconAppointment().getFalconLocation().getName();
			falconAppointmentPatron.getFalconAppointment().getFalconService().getName();
			falconAppointmentPatron.getFalconAppointment().getFalconStaff().getName();
		}
		return falconAppointment;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAppointment(Integer id) {
		FalconAppointment appointment = new FalconAppointment();
		appointment.setId(id);
		List<FalconAppointmentPatron> patrons = falconAppointmentPatronRepository.findByFalconAppointment(appointment);
		for(FalconAppointmentPatron patron : patrons){
			falconAppointmentPatronRepository.delete(patron);
		}
		falconAppointmentRepository.delete(appointment);
	}

	@Override
	@Transactional(readOnly = true)
	public FalconAppointmentPatron findPatron(Integer id) {
		FalconAppointmentPatron falconAppointmentPatron = falconAppointmentPatronRepository.findOne(id);
		falconAppointmentPatron.getFalconAppointment().getFalconStaff().getName();
		falconAppointmentPatron.getFalconAppointment().getFalconLocation().getName();
		falconAppointmentPatron.getFalconPatron().getFalconUserByPatron().getName();
		return falconAppointmentPatron;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAppointmentPatron(Integer id) {
		FalconAppointmentPatron appointmentPatron = new FalconAppointmentPatron();
		appointmentPatron.setId(id);
		falconAppointmentPatronRepository.delete(appointmentPatron);
	}

}
