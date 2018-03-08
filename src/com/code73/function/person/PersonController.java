package com.code73.function.person;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.code73.function.dto.Person;

public interface PersonController {
	
	Response findPerson(String jwt, String username, String type);

	Response deletePerson(String jwt, String username);

	Response updatePerson(UriInfo uriInfo, String jwt, String username, Person person);

	Response createPerson(UriInfo uriInfo, String jwt, Person person);

}
