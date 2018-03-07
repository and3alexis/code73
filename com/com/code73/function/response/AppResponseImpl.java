package com.code73.function.response;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
public class AppResponseImpl implements AppResponse{
	
	public Response generatePostResponse(Object object, String type){
		Response response = Response
				.status(Response.Status.CREATED)
				.entity(object)
				.type(type != null && type.toUpperCase().equals("XML") ? MediaType.APPLICATION_XML:MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST")
				.build();
		return response;
	}
	
	public Response generatePutResponse(Object object, String type){
		Response response = Response
				.status(Response.Status.CREATED)
				.entity(object)
				.type(type != null && type.toUpperCase().equals("XML") ? MediaType.APPLICATION_XML:MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT")
				.build();
		return response;
	}
	
	public Response generateGetResponse(Object object, String type){
		Response response = Response
				.status(Response.Status.OK)
				.entity(object)
				.type(type != null && type.toUpperCase().equals("XML") ? MediaType.APPLICATION_XML:MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET")
				.build();
		return response;
	}
	
	@Override
	public Response generateGetNotFoundResponse(Object object, String type){
		Response response = Response
				.status(Response.Status.NOT_FOUND)
				.entity(object)
				.type(type != null && type.toUpperCase().equals("XML") ? MediaType.APPLICATION_XML:MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET")
				.build();
		return response;
	}
	
	public Response generateDeleteResponse(Object object, String type){
		Response response = Response
				.status(Response.Status.NO_CONTENT)
				.entity(object)
				.type(type != null && type.toUpperCase().equals("XML") ? MediaType.APPLICATION_XML:MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "DELETE")
				.build();
		return response;
	}
	
	@Override
	public Response generateUnAuthorizedResponse(Object object, String type){
		Response response = Response
				.status(Response.Status.UNAUTHORIZED)
				.entity(object)
				.type(type != null && type.toUpperCase().equals("XML") ? MediaType.APPLICATION_XML:MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.build();
		return response;
	}

}
