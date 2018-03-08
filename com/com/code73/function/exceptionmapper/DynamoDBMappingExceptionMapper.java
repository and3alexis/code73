package com.code73.function.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.code73.function.response.EchoResponse;

@Provider
@Component
public class DynamoDBMappingExceptionMapper implements ExceptionMapper<DynamoDBMappingException>{
	
	@Override
	public Response toResponse(DynamoDBMappingException exception) {
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(exception.getMessage());
		echoResponse.setStatus(430);
		
		Response response = Response
				.status(430)
				.entity(echoResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
		return response;
	}

}
