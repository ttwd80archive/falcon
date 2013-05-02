package com.twistlet.falcon.model.repository;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.twistlet.falcon.model.entity.FalconAppointment;
import com.twistlet.falcon.model.entity.FalconAppointmentPatron;
import com.twistlet.falcon.model.entity.FalconLocation;
import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconRole;
import com.twistlet.falcon.model.entity.FalconService;
import com.twistlet.falcon.model.entity.FalconStaff;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.entity.FalconUserRole;

public class FalconAppointmentRepositoryImplTest extends AbstractFalconRepositoryTest {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	FalconAppointmentRepository falconAppointmentRepository;

	FalconRole role1, role3;
	
	FalconUser user1, user2, user3, admin1;
	
	FalconPatron patron1, patron2, patron3;
	
	FalconLocation location1;
	
	FalconService service1;
	
	FalconStaff staff1;
	
	FalconAppointment appointment1, appointment2;
	
	@Before
	public void init() {
		entityManager.persist(role1 = createNewRole("ROLE_LEVEL_1"));
		entityManager.persist(createNewRole("ROLE_LEVEL_2"));
		entityManager.persist(role3 = createNewRole("ROLE_LEVEL_3"));
		entityManager.persist(user1 = createNewUser("USER_1"));
		entityManager.persist(user2 = createNewUser("USER_2"));
		entityManager.persist(user3 = createNewUser("USER_3"));
		entityManager.persist(admin1 = createNewUser("ADMIN_1"));
		entityManager.persist(new FalconUserRole(user1, role1));
		entityManager.persist(new FalconUserRole(user2, role1));
		entityManager.persist(new FalconUserRole(user3, role1));
		entityManager.persist(new FalconUserRole(user2, role3));
		entityManager.persist(patron1 = createNewPatron(user1, admin1));
		entityManager.persist(patron2 = createNewPatron(user2, admin1));
		entityManager.persist(patron3 = createNewPatron(user3, admin1));
		entityManager.persist(location1 = createNewLocation("LOCATION_1", admin1));
		entityManager.persist(service1 = createNewService("SERVICE_1", admin1));
		entityManager.persist(staff1 = createNewStaff("STAFF_1", admin1));
		Date now = new Date();
		entityManager.persist(appointment1 = createNewAppointment(now, DateUtils.addHours(now , 4), staff1, service1, location1));
		entityManager.persist(appointment2 = createNewAppointment(DateUtils.addHours(now, 3), DateUtils.addHours(now , 5), staff1, service1, location1));
		entityManager.persist(createNewFalconAppointmentPatron(patron1, appointment1));
		entityManager.persist(createNewFalconAppointmentPatron(patron2, appointment1));
		entityManager.persist(createNewFalconAppointmentPatron(patron3, appointment1));
		entityManager.persist(createNewFalconAppointmentPatron(patron1, appointment2));
		entityManager.persist(createNewFalconAppointmentPatron(patron2, appointment2));
		entityManager.persist(createNewFalconAppointmentPatron(patron3, appointment2));
		entityManager.flush();
		entityManager.clear();
	}
	
	private FalconUser createNewUser(final String username) {
		final FalconUser falconUser = new FalconUser();
		falconUser.setUsername(username);
		falconUser.setPassword("x");
		falconUser.setName(username);
		return falconUser;
	}

	private FalconRole createNewRole(final String rolename) {
		final FalconRole falconRole = new FalconRole();
		falconRole.setRoleName(rolename);
		return falconRole;
	}

	private FalconPatron createNewPatron(final FalconUser patron, final FalconUser admin){
		final FalconPatron falconPatron = new FalconPatron();
		falconPatron.setFalconUserByPatron(patron);
		falconPatron.setFalconUserByAdmin(admin);
		return falconPatron;
	}
	
	private FalconLocation createNewLocation(final String location, final FalconUser admin){
		FalconLocation falconLocation = new FalconLocation();
		falconLocation.setName(location);
		falconLocation.setFalconUser(admin);
		return falconLocation;
	}
	
	private FalconService createNewService(final String service, final FalconUser admin){
		FalconService falconService = new FalconService();
		falconService.setName(service);
		falconService.setFalconUser(admin);
		return falconService;
	}
	
	private FalconStaff createNewStaff(final String name, final FalconUser admin){
		FalconStaff falconStaff = new FalconStaff();
		falconStaff.setName(name);
		falconStaff.setFalconUser(admin);
		falconStaff.setNric("XXX");
		return falconStaff;
	}
	
	private FalconAppointmentPatron createNewFalconAppointmentPatron(final FalconPatron patron, final FalconAppointment appointment){
		FalconAppointmentPatron appointmentPatron = new FalconAppointmentPatron();
		appointmentPatron.setFalconPatron(patron);
		appointmentPatron.setFalconAppointment(appointment);
		return appointmentPatron;
	}
	
	private FalconAppointment createNewAppointment(final Date start, final Date end, final FalconStaff staff, final FalconService service, final FalconLocation location){
		FalconAppointment appointment = new FalconAppointment();
		appointment.setAppointmentDate(start);
		appointment.setAppointmentDateEnd(end);
		appointment.setFalconLocation(location);
		appointment.setFalconService(service);
		appointment.setFalconStaff(staff);
		return appointment;
		
	}
	
	@Test
	public void testListAppointmentsByStaff(){
		List<FalconAppointment> appointments  = falconAppointmentRepository.listAppointmentsByParam(staff1.getId(), null, null, null, null);
		assertEquals(2, appointments.size());
	}
	
	@Test
	public void testListAppointmentsByPatron(){
		List<FalconAppointment> appointments  = falconAppointmentRepository.listAppointmentsByParam(null, patron1.getFalconUserByPatron().getUsername(), null, null, null);
		assertEquals(2, appointments.size());
	}
	
	@Test
	public void testListAppointmentsByDate(){
		final Date now = new Date();
		final Date searchDate = DateUtils.addHours(now, 1);
		System.out.println("start:" + appointment1.getAppointmentDate());
		System.out.println("end:" + appointment1.getAppointmentDateEnd());
		System.out.println("search:" + searchDate);
		List<FalconAppointment> appointments  = falconAppointmentRepository.listAppointmentsByParam(null, null, null, null, searchDate);
		assertEquals(1, appointments.size());
	}
	
	@Test
	public void testListAppointmentsByLocation(){
		List<FalconAppointment> appointments  = falconAppointmentRepository.listAppointmentsByParam(null, null, null, location1.getId(), null);
		assertEquals(2, appointments.size());
	}
	
	@Test
	public void testListAppointmentsByService(){
		List<FalconAppointment> appointments  = falconAppointmentRepository.listAppointmentsByParam(null, null, service1.getId(), null, null);
		assertEquals(2, appointments.size());
	}
	
	@Test
	public void testListAppointmentsByServiceAndDate(){
		final Date now = new Date();
		final Date searchDate = DateUtils.addHours(now, 1);
		List<FalconAppointment> appointments  = falconAppointmentRepository.listAppointmentsByParam(null, null, service1.getId(), null, searchDate);
		assertEquals(1, appointments.size());
	}
	
	@Test
	public void testListFullByAppointmentDateBetween(){
		final Date now = new Date();
		final Date start = DateUtils.truncate(now, Calendar.MONTH);
		final Date end = DateUtils.addSeconds(DateUtils.ceiling(now, Calendar.MONTH), -1);
		List<FalconAppointment> appointments  = falconAppointmentRepository.listFullByAppointmentDateBetween(start, end);
		assertEquals(2, appointments.size());
	}
}
