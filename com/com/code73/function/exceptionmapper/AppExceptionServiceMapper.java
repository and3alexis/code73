package com.code73.function.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.code73.function.messages.Messages;
import com.code73.function.person.KeyMessage;
import com.code73.function.response.EchoResponse;

@Provider
@Component
public class AppExceptionServiceMapper implements ExceptionMapper<Exception>{
	
	@Autowired
	private Messages messages;

	@Override
	public Response toResponse(Exception exception) {
		String message = this.messages.getString(KeyMessage.GENERAL_ATTIBUTE_MESSAGE_VALUE_1);
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(exception.getClass().getName()+" "+(exception.getMessage() == null ? message:exception.getMessage()));
		echoResponse.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		
		Response response = Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(echoResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
		return response;
	}

}
