package com.twistlet.falcon.security.service;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.twistlet.falcon.model.repository.FalconUserRepository;
import com.twistlet.falcon.model.repository.FalconUserRoleRepository;

public class UserDetailsServiceImplTest {

	private FalconUserRepository falconUserRepository;

	private FalconUserRoleRepository falconUserRoleRepository;

	private UserDetailsServiceImpl unit;

	@Before
	public void init() {
		falconUserRepository = EasyMock
				.createStrictMock(FalconUserRepository.class);
		falconUserRoleRepository = EasyMock
				.createStrictMock(FalconUserRoleRepository.class);
		unit = new UserDetailsServiceImpl(falconUserRepository,
				falconUserRoleRepository);
	}

	@After
	public void verify() {
		EasyMock.verify(falconUserRepository, falconUserRoleRepository);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void testLoadUserByUsernameNotFound() {
		EasyMock.expect(
				falconUserRepository.findOne(EasyMock.anyObject(String.class)))
				.andReturn(null);
		EasyMock.replay(falconUserRepository, falconUserRoleRepository);
		unit.loadUserByUsername("invalid");
		fail("Not yet implemented");
	}

}
