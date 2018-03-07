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
public class AppExceptionMapper implements ExceptionMapper<AppException>{
	
	@Autowired
	private Messages messages;

	@Override
	public Response toResponse(AppException exception) {
		String message = this.messages.getString(KeyMessage.GENERAL_ATTIBUTE_MESSAGE_VALUE_1);
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(exception.getCause().getClass().getName()+" "+(exception.getMessage() == null ? message:exception.getMessage()));
		echoResponse.setStatus(430);
		
		Response response = Response
				.status(430)
				.entity(echoResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
		return response;
	}

}
