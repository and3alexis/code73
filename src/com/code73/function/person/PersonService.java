package com.code73.function.person;

import com.code73.function.dto.Person;

public interface PersonService {
	
	public void createPerson(Person person);

	public Person findPerson(String username);

	public byte deletePerson(String username);

	public byte updatePersonByUsername(String username, Person person);

}
