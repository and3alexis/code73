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
import com.code73.function.response.dto.CreatedResponse;
import com.code73.function.response.dto.DeletedResponse;
import com.code73.function.security.Security;

@Controller
@Path("/people")
public class PersonControllerImpl implements PersonController{
	
	private PersonService personService;
	
	private AppResponse appResponse;
	
	private Security security;
	
	@Autowired
	public PersonControllerImpl(PersonService personService, AppResponse appResponse, Security security) {
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
			CreatedResponse created = new CreatedResponse();
			created.setLocation("http://localhost:8080/code73/api/people/"+person.getUsername());
			created.setMessage("Person " + person.getUsername() + " was created");
			created.setStatus(Response.Status.CREATED.getStatusCode());
			
			return appResponse.generatePostResponse(created, null);
		}
		DeletedResponse deleted = new DeletedResponse();
		deleted.setMessage("JWT " + jwt + " is invalid");
		deleted.setStatus(Response.Status.NO_CONTENT.getStatusCode());
		return appResponse.generateUnAuthorizedResponse(deleted, null);
	}

	@GET
	@Path("/{username}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response findPerson(@HeaderParam("jwt") String jwt, @PathParam("username") String username, @QueryParam("type") String type) {
		if(security.verify(jwt)){
			Person person = personService.findPerson(username);
			if(person == null){
				DeletedResponse deleted = new DeletedResponse();
				deleted.setMessage("Person " + username + " is not found");
				deleted.setStatus(Response.Status.NOT_FOUND.getStatusCode());
				return appResponse.generateGetNotFoundResponse(deleted, type);
			}
			return appResponse.generateGetResponse(person, type);
		}
		DeletedResponse deleted = new DeletedResponse();
		deleted.setMessage("JWT " + jwt + " is invalid");
		deleted.setStatus(Response.Status.NO_CONTENT.getStatusCode());
		return appResponse.generateUnAuthorizedResponse(deleted, null);
	}

	@PUT
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePerson(@HeaderParam("jwt") String jwt, @PathParam("username") String username, Person person) {
		if(security.verify(jwt)){
			CreatedResponse created = null;
			byte result = this.personService.updatePersonByUsername(username, person);
			if(result == 0){
				created = new CreatedResponse();
				created.setLocation("http://localhost:8080/code73/api/people/"+username);
				created.setMessage("Person " + person.getUsername() + " was created");
				created.setStatus(Response.Status.CREATED.getStatusCode());
			}else{
				created = new CreatedResponse();
				created.setLocation("http://localhost:8080/code73/api/people/"+username);
				created.setMessage("Person " + person.getUsername() + " was update");
				created.setStatus(Response.Status.CREATED.getStatusCode());
			}
			
			return appResponse.generatePutResponse(created, null);
		}
		DeletedResponse deleted = new DeletedResponse();
		deleted.setMessage("JWT " + jwt + " is invalid");
		deleted.setStatus(Response.Status.NO_CONTENT.getStatusCode());
		return appResponse.generateUnAuthorizedResponse(deleted, null);
	}

	@DELETE
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePerson(@HeaderParam("jwt") String jwt, @PathParam("username") String username) {
		if(security.verify(jwt)){
			byte result = personService.deletePerson(username);
			if(result == 0){
				DeletedResponse deleted = new DeletedResponse();
				deleted.setMessage("Person " + username + " is not found");
				deleted.setStatus(Response.Status.NOT_FOUND.getStatusCode());
				return appResponse.generateGetNotFoundResponse(deleted, null);
			}
			
			DeletedResponse deleted = new DeletedResponse();
			deleted.setMessage("Person " + username + " was deleted");
			deleted.setStatus(Response.Status.NO_CONTENT.getStatusCode());
			return appResponse.generateDeleteResponse(deleted, null);
		}
		DeletedResponse deleted = new DeletedResponse();
		deleted.setMessage("JWT " + jwt + " is invalid");
		deleted.setStatus(Response.Status.NO_CONTENT.getStatusCode());
		return appResponse.generateUnAuthorizedResponse(deleted, null);
		
	}

}
