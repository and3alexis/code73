package com.code73.function.person;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.code73.function.TestConstant;
import com.code73.function.dto.Mobile;
import com.code73.function.dto.Password;
import com.code73.function.dto.Person;
import com.code73.function.dto.Role;

public class PersonServiceTest {
	
	@Mock
	private PersonDAO personDAO;
	
	private PersonService personService;
	
	private Person person;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.personService = new PersonServiceimpl(personDAO);
		
		Person person = new Person();
		person.setEmail(TestConstant.PERSON_EMAIL);
		person.setLastname(TestConstant.PERSON_LASTNAME);
		person.setMobile(new Mobile());
		person.setName(TestConstant.PERSON_NAME);
		person.setPassword(new Password());
		person.setRole(new Role());
		person.setUsername(TestConstant.PERSON_USERNAME);
		this.person = person;
		
		Mockito.when(this.personDAO.findPerson(this.person.getUsername())).thenReturn(this.person);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreatePerson() {
		byte result = this.personService.createPerson(person);
		assertEquals(1, result);
	}

	@Test
	public void testFindPerson() {
		Person person = this.personService.findPerson(this.person.getUsername());
		assertNotNull(person);
	}

	@Test
	public void testDeletePersonNotFound() {
		byte result = this.personService.deletePerson("asdfghj");
		assertEquals(0, result);
	}
	
	@Test
	public void testDeletePerson() {
		byte result = this.personService.deletePerson(this.person.getUsername());
		assertEquals(1, result);
	}

	@Test
	public void testUpdatePersonByUsernameNotFound() {
		byte result = this.personService.updatePersonByUsername("asdasdad", person);
		assertEquals(0, result);
	}
	
	@Test
	public void testUpdatePersonByUsername() {
		byte result = this.personService.updatePersonByUsername(this.person.getUsername(), person);
		assertEquals(1, result);
	}

}
