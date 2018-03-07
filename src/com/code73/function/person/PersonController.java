package com.code73.function.person;

import javax.ws.rs.core.Response;

import com.code73.function.dto.Person;

public interface PersonController {
	
	Response createPerson(String jwt, Person person);

	Response findPerson(String jwt, String username, String type);

	Response deletePerson(String jwt, String username);

	Response updatePerson(String jwt, String username, Person person);

}
