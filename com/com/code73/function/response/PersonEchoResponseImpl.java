package com.code73.function.response;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.code73.function.dto.Person;
import com.code73.function.messages.KeyMessage;
import com.code73.function.messages.Messages;

@Component
public class PersonEchoResponseImpl implements PersonEchoResponse {
	
	@Autowired
	private AppResponse appResponse;

	@Autowired
	private Messages messages;

	public Response created(UriInfo uriInfo, String username) {
		final URI baseUri = uriInfo.getBaseUri();
		final String restResource = this.messages.getString(KeyMessage.PERSON_RESOURCE_PATH_VALUE, new Object[]{username});
		String message = this.messages.getString(KeyMessage.PERSON_ATTIBUTE_MESSAGE_VALUE_1, new Object[]{username});
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(message);
		echoResponse.setStatus(Response.Status.CREATED.getStatusCode());
		echoResponse.setContent(baseUri+restResource);
		
		Response response = appResponse.generatePostResponse(echoResponse, null);
		return response;
	}
	
	public Response notFound(String username) {
		String message = this.messages.getString(KeyMessage.PERSON_ATTIBUTE_MESSAGE_VALUE_2, new Object[]{username});
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(message);
		echoResponse.setStatus(Response.Status.NOT_FOUND.getStatusCode());
		
		Response response = appResponse.generateGetNotFoundResponse(echoResponse, null);
		return response;
	}

	public Response updated(UriInfo uriInfo, String username) {
		final URI baseUri = uriInfo.getBaseUri();
		final String restResource = this.messages.getString(KeyMessage.PERSON_RESOURCE_PATH_VALUE, new Object[]{username});
		String message = this.messages.getString(KeyMessage.PERSON_ATTIBUTE_MESSAGE_VALUE_3, new Object[]{username});
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(message);
		echoResponse.setStatus(Response.Status.CREATED.getStatusCode());
		echoResponse.setContent(baseUri+restResource);
		
		Response response = appResponse.generatePutResponse(echoResponse, null);
		return response;
	}

	public Response deleted(String username) {
		String message = this.messages.getString(KeyMessage.PERSON_ATTIBUTE_MESSAGE_VALUE_4, new Object[]{username});
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(message);
		echoResponse.setStatus(Response.Status.NO_CONTENT.getStatusCode());
		
		Response response = appResponse.generateDeleteResponse(echoResponse, null);
		return response;
	}

	@Override
	public Response found(Person person, String type) {
		String message = this.messages.getString(KeyMessage.GENERAL_ATTIBUTE_MESSAGE_VALUE_3);
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(message);
		echoResponse.setContent(person);
		echoResponse.setStatus(Response.Status.OK.getStatusCode());
		
		Response response = appResponse.generateGetResponse(echoResponse, type);
		
		return response;
	}
	
	@Override
	public Response generateUnAuthorizedResponse(String jwt){
		Response response = appResponse.generateUnAuthorizedResponse(jwt, null);
		return response;
	}

}
