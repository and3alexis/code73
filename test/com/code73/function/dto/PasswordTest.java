package com.code73.function.dto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.code73.function.TestConstant;

public class PasswordTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Password password = new Password();
		password.setEncrypt(TestConstant.PASSWORD_ENCRYPT);
		password.setValue(TestConstant.PASSWORD_VALUE);
		
		assertEquals(TestConstant.PASSWORD_ENCRYPT, password.getEncrypt());
		assertEquals(TestConstant.PASSWORD_VALUE, password.getValue());
	}

}
