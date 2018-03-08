package com.code73.function.dto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.code73.function.TestConstant;

public class CountryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCountryEntity() {
		Country country = new Country();
		country.setCallingCode(TestConstant.COUNTRY_CALLING_CODE);
		country.setName(TestConstant.COUNTRY_NAME);
		country.setNumericCode(TestConstant.COUNTRY_NUMERIC_CODE);
		
		assertEquals(TestConstant.COUNTRY_CALLING_CODE, country.getCallingCode());
		assertEquals(TestConstant.COUNTRY_NAME, country.getName());
		assertEquals(TestConstant.COUNTRY_NUMERIC_CODE, country.getNumericCode());
	}

}
