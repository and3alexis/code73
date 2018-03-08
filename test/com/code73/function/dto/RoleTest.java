package com.code73.function.dto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.code73.function.TestConstant;

public class RoleTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRole() {
		Role role = new Role();
		role.setCode(TestConstant.ROLE_CODE);
		role.setTitle(TestConstant.ROLE_TITLE);
		
		assertEquals(TestConstant.ROLE_CODE, role.getCode());
		assertEquals(TestConstant.ROLE_TITLE, role.getTitle());
	}

}
