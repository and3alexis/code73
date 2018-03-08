package com.code73.function.dto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.code73.function.TestConstant;

public class PersonTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPersonEntity() {
		Person person = new Person();
		person.setEmail(TestConstant.PERSON_EMAIL);
		person.setLastname(TestConstant.PERSON_LASTNAME);
		person.setMobile(new Mobile());
		person.setName(TestConstant.PERSON_NAME);
		person.setPassword(new Password());
		person.setRole(new Role());
		person.setUsername(TestConstant.PERSON_USERNAME);
		
		assertEquals(TestConstant.PERSON_EMAIL, person.getEmail());
		assertEquals(TestConstant.PERSON_LASTNAME, person.getLastname());
		assertEquals(TestConstant.PERSON_NAME, person.getName());
		assertEquals(TestConstant.PERSON_USERNAME, person.getUsername());
		assertNotNull(person.getPassword());
		assertNotNull(person.getMobile());
		assertNotNull(person.getRole());
		assertNotNull(person.getLink());
	}
	
	@Test
	public void testPersonEntityCopy() {
		Person currentPerson = new Person();
		currentPerson.setEmail(TestConstant.PERSON_EMAIL);
		currentPerson.setLastname(TestConstant.PERSON_LASTNAME);
		currentPerson.setMobile(new Mobile());
		currentPerson.setName(TestConstant.PERSON_NAME);
		currentPerson.setPassword(new Password());
		currentPerson.setRole(new Role());
		currentPerson.setUsername(TestConstant.PERSON_USERNAME);
		
		Person person = new Person();
		person.setUsername(TestConstant.PERSON_USERNAME);
		person.updateFrom(currentPerson);
		
		assertEquals(TestConstant.PERSON_EMAIL, person.getEmail());
		assertEquals(TestConstant.PERSON_LASTNAME, person.getLastname());
		assertEquals(TestConstant.PERSON_NAME, person.getName());
		assertEquals(TestConstant.PERSON_USERNAME, person.getUsername());
		assertNotNull(person.getPassword());
		assertNotNull(person.getMobile());
		assertNotNull(person.getRole());
	}

}
