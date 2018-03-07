package com.code73.function.person;

import com.code73.function.dto.Person;

public interface PersonDAO {

	void createPerson(Person person);

	Person findPerson(String username);

	void deletePerson(String username);

	void updatePerson(String username, Person person);

	void deletePerson(Person person);

}
