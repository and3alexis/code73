package com.code73.function.response;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.code73.function.dto.Person;

public interface PersonEchoResponse {
	
	public Response created(UriInfo uriInfo, String username);
	
	public Response notFound(String username);
	
	public Response updated(UriInfo uriInfo, String username);

	public Response deleted(String username);

	Response generateUnAuthorizedResponse(String jwt);

	Response found(Person person, String type);

}
