package com.code73.function.dto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.code73.function.TestConstant;

public class MobileTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMobileEntity() {
		Mobile mobile = new Mobile();
		mobile.setCountry(new Country());
		mobile.setValue(TestConstant.MOBILE_VALUE);
		
		assertNotNull(mobile.getCountry());
		assertEquals(TestConstant.MOBILE_VALUE, mobile.getValue());
	}

}
