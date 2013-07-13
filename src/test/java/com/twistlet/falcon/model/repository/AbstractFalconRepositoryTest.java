package com.twistlet.falcon.model.repository;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration({ "classpath:test-falcon-spring-config.xml", "classpath:falcon-spring-data-source-test.xml",
		"classpath:falcon-spring-entity-manager.xml", "classpath:falcon-spring-data-jpa.xml" })
public abstract class AbstractFalconRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

}
