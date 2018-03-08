package com.code73.function.person;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.code73.function.dto.Person;
import com.code73.function.response.PersonEchoResponse;
import com.code73.function.security.Security;

@Controller
@Path("people")
public class PersonControllerImpl implements PersonController{
	
	private PersonEchoResponse personEchoResponse;
	
	private PersonService personService;
	
	private Security security;

	@Autowired
	public PersonControllerImpl(PersonEchoResponse personEchoResponse, PersonService personService, Security security) {
		this.personEchoResponse = personEchoResponse;
		this.personService = personService;
		this.security = security;
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPerson(@Context UriInfo uriInfo, @HeaderParam("jwt") String jwt, Person person) {
		if(security.verify(jwt)){
			Person currentPerson = personService.findPerson(person.getUsername());
			if(currentPerson == null){
				this.personService.createPerson(person);
				return personEchoResponse.created(uriInfo, person.getUsername());
			}
			return personEchoResponse.updated(uriInfo, person.getUsername());
		}
		return personEchoResponse.generateUnAuthorizedResponse(jwt);
	}

	@GET
	@Path("/{username}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response findPerson(@HeaderParam("jwt") String jwt, @PathParam("username") String username, @QueryParam("type") String type) {
		if(security.verify(jwt)){
			Person person = personService.findPerson(username);
			if(person == null){
				return personEchoResponse.notFound(username);
			}
			return personEchoResponse.found(person, type);
		}
		return personEchoResponse.generateUnAuthorizedResponse(jwt);
	}

	@PUT
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePerson(@Context UriInfo uriInfo, @HeaderParam("jwt") String jwt, @PathParam("username") String username, Person person) {
		Response response = null;
		if(security.verify(jwt)){
			byte result = this.personService.updatePersonByUsername(username, person);
			if(result == 0){
				response = personEchoResponse.created(uriInfo, username);
			}else{
				response = personEchoResponse.updated(uriInfo, username);
			}
			return response;
		}
		return personEchoResponse.generateUnAuthorizedResponse(jwt);
	}

	@DELETE
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePerson(@HeaderParam("jwt") String jwt, @PathParam("username") String username) {
		if(security.verify(jwt)){
			byte result = personService.deletePerson(username);
			if(result == 0){
				return personEchoResponse.notFound(username);
			}
			return personEchoResponse.deleted(username);
		}
		return personEchoResponse.generateUnAuthorizedResponse(jwt);
	}

}
