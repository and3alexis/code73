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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.code73.function.dto.Person;
import com.code73.function.response.AppResponse;
import com.code73.function.response.EchoResponse;
import com.code73.function.response.PersonEchoResponse;
import com.code73.function.security.Security;

@Controller
@Path("/people")
public class PersonControllerImpl implements PersonController{
	
	private PersonEchoResponse personEchoResponse;
	
	private PersonService personService;
	
	private AppResponse appResponse;
	
	private Security security;
	
	@Autowired
	public PersonControllerImpl(PersonEchoResponse personEchoResponse, PersonService personService,
			AppResponse appResponse, Security security) {
		this.personEchoResponse = personEchoResponse;
		this.personService = personService;
		this.appResponse = appResponse;
		this.security = security;
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPerson(@HeaderParam("jwt") String jwt, Person person) {
		if(security.verify(jwt)){
			this.personService.createPerson(person);
			EchoResponse echoResponse = personEchoResponse.created(person.getUsername());
			return appResponse.generatePostResponse(echoResponse, null);
		}
		
		return appResponse.generateUnAuthorizedResponse(jwt, null);
	}

	@GET
	@Path("/{username}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response findPerson(@HeaderParam("jwt") String jwt, @PathParam("username") String username, @QueryParam("type") String type) {
		if(security.verify(jwt)){
			Person person = personService.findPerson(username);
			if(person == null){
				EchoResponse echoResponse = personEchoResponse.notFound(username);
				return appResponse.generateGetNotFoundResponse(echoResponse, type);
			}
			return appResponse.generateGetResponse(person, type);
		}
		return appResponse.generateUnAuthorizedResponse(jwt, null);
	}

	@PUT
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePerson(@HeaderParam("jwt") String jwt, @PathParam("username") String username, Person person) {
		if(security.verify(jwt)){
			EchoResponse echoResponse = null;
			byte result = this.personService.updatePersonByUsername(username, person);
			if(result == 0){
				echoResponse = personEchoResponse.created(username);
			}else{
				echoResponse = personEchoResponse.updated(username);
			}
			
			return appResponse.generatePutResponse(echoResponse, null);
		}
		
		return appResponse.generateUnAuthorizedResponse(jwt, null);
	}

	@DELETE
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePerson(@HeaderParam("jwt") String jwt, @PathParam("username") String username) {
		if(security.verify(jwt)){
			byte result = personService.deletePerson(username);
			if(result == 0){
				EchoResponse echoResponse = personEchoResponse.notFound(username);
				return appResponse.generateGetNotFoundResponse(echoResponse, null);
			}
			
			EchoResponse echoResponse = personEchoResponse.deleted(username);
			return appResponse.generateDeleteResponse(echoResponse, null);
		}
		return appResponse.generateUnAuthorizedResponse(jwt, null);
		
	}

}
