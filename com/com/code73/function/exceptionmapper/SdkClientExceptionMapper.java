package com.code73.function.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.SdkClientException;
import com.code73.function.messages.KeyMessage;
import com.code73.function.messages.Messages;
import com.code73.function.response.EchoResponse;

@Provider
@Component
public class SdkClientExceptionMapper implements ExceptionMapper<SdkClientException>{
	
	@Autowired
	private Messages messages;

	@Override
	public Response toResponse(SdkClientException exception) {
		String message = this.messages.getString(KeyMessage.GENERAL_ATTIBUTE_MESSAGE_VALUE_2);
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(exception.getCause().getClass().getSimpleName()+" "+message);
		echoResponse.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		
		Response response = Response
				.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
				.entity(echoResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
		return response;
	}

}
