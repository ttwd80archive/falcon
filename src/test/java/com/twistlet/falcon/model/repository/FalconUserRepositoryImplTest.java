package com.twistlet.falcon.model.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.twistlet.falcon.model.entity.FalconPatron;
import com.twistlet.falcon.model.entity.FalconRole;
import com.twistlet.falcon.model.entity.FalconUser;
import com.twistlet.falcon.model.entity.FalconUserRole;

public class FalconUserRepositoryImplTest extends AbstractFalconRepositoryTest {

	@Autowired
	FalconUserRepository falconUserRepository;

	@PersistenceContext
	EntityManager entityManager;

	@Before
	public void init() {
		FalconRole role1, role3;
		FalconUser user1, user2, user3;
		entityManager.persist(role1 = createNewRole("ROLE_LEVEL_1"));
		entityManager.persist(createNewRole("ROLE_LEVEL_2"));
		entityManager.persist(role3 = createNewRole("ROLE_LEVEL_3"));
		entityManager.persist(user1 = createNewUser("USER_1", "email1@add.com", "1", "1"));
		entityManager.persist(user2 = createNewUser("USER_2", "email2@add.com", "2", "2"));
		entityManager.persist(user3 = createNewUser("USER_3", "email3@add.com", "3", "3"));
		// 3 users have role 1
		entityManager.persist(new FalconUserRole(user1, role1));
		entityManager.persist(new FalconUserRole(user2, role1));
		entityManager.persist(new FalconUserRole(user3, role1));

		// no user has role 2

		// 1 user has role 3
		entityManager.persist(new FalconUserRole(user2, role3));

		entityManager.persist(createPatron(user1, user2));
		entityManager.persist(createPatron(user2, user3));

		entityManager.flush();
		entityManager.clear();
	}

	private FalconPatron createPatron(FalconUser admin, FalconUser patron) {
		FalconPatron falconPatron = new FalconPatron();
		falconPatron.setFalconUserByAdmin(admin);
		falconPatron.setFalconUserByPatron(patron);
		return falconPatron;
	}

	private FalconUser createNewUser(final String username, final String email, final String nric, final String phone) {
		final FalconUser falconUser = new FalconUser();
		falconUser.setUsername(username);
		falconUser.setPassword("x");
		falconUser.setName(username);
		falconUser.setEmail(email);
		falconUser.setNric(nric);
		falconUser.setPhone(phone);
		falconUser.setValid(true);
		return falconUser;
	}

	private FalconRole createNewRole(final String rolename) {
		final FalconRole falconRole = new FalconRole();
		falconRole.setRoleName(rolename);
		return falconRole;
	}

	@Test
	public void testFindByRolenameNoneFoundTotallyInvalid() {
		final List<FalconUser> list = falconUserRepository.findByRolenameAndNameLike("XXX", "");
		assertEquals(0, list.size());
	}

	@Test
	public void testFindByRolenameNoneFoundButValidRole() {
		final List<FalconUser> list = falconUserRepository.findByRolenameAndNameLike("ROLE_LEVEL_2", "");
		assertEquals(0, list.size());
	}

	@Test
	public void testFindByRolenameNoneFoundButValidRoleValidName() {
		final List<FalconUser> list = falconUserRepository.findByRolenameAndNameLike("ROLE_LEVEL_2", "USER_1");
		assertEquals(0, list.size());
	}

	@Test
	public void testFindByRolenameShouldExistCount() {
		final List<FalconUser> list = falconUserRepository.findByRolenameAndNameLike("ROLE_LEVEL_3", "");
		assertEquals(1, list.size());
	}

	@Test
	public void testFindByRolenameShouldExist3Count() {
		final List<FalconUser> list = falconUserRepository.findByRolenameAndNameLike("ROLE_LEVEL_1", "");
		assertEquals(3, list.size());
	}

	@Test
	public void testFindByRolenameShouldExist3Values() {
		final List<FalconUser> list = falconUserRepository.findByRolenameAndNameLike("ROLE_LEVEL_1", "");
		final Set<String> set = new LinkedHashSet<>();
		for (final FalconUser falconUser : list) {
			set.add(falconUser.getUsername());
		}
		final String joined = StringUtils.join(set, " ");
		assertEquals("USER_1 USER_2 USER_3", joined);
	}

	@Test
	public void testFindByRolenameShouldExistSingleCount() {
		final List<FalconUser> list = falconUserRepository.findByRolenameAndNameLike("ROLE_LEVEL_1", "2");
		assertEquals(1, list.size());
	}

	@Test
	public void testFindByRolenameShouldExistSingleValue() {
		final List<FalconUser> list = falconUserRepository.findByRolenameAndNameLike("ROLE_LEVEL_1", "2");
		assertEquals("USER_2", list.get(0).getName());
	}

	@Test
	public void testFindByAdminBad() {
		assertEquals(0, falconUserRepository.findByAdmin("cartman").size());
	}

	@Test
	public void testFindByAdmin1Size() {
		List<FalconUser> list = falconUserRepository.findByAdmin("USER_1");
		assertEquals(1, list.size());
	}

	@Test
	public void testFindByAdmin1Content() {
		List<FalconUser> list = falconUserRepository.findByAdmin("USER_1");
		FalconUser patron = list.get(0);
		assertEquals("email2@add.com", patron.getEmail());
	}

	@Test
	public void testFindByAdmin2Size() {
		List<FalconUser> list = falconUserRepository.findByAdmin("USER_2");
		assertEquals(1, list.size());
	}

	@Test
	public void testFindByAdmin2Content() {
		List<FalconUser> list = falconUserRepository.findByAdmin("USER_2");
		FalconUser patron = list.get(0);
		assertEquals("email3@add.com", patron.getEmail());
	}
	
	@Test
	public void testFindPhone() {
		List<FalconUser> list = falconUserRepository.findByPhone("3");
		FalconUser patron = list.get(0);
		assertEquals("3", patron.getNric());
	}

}
