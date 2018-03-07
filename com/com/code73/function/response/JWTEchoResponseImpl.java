package com.code73.function.response;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.code73.function.messages.Messages;
import com.code73.function.person.KeyMessage;

@Component
public class JWTEchoResponseImpl implements JWTEchoResponse{
	
	@Autowired
	private Messages messages;

	public EchoResponse unAuthorized(String jwt) {
		String message = this.messages.getString(KeyMessage.JWT_ATTIBUTE_MESSAGE_VALUE_1, new Object[]{jwt});
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(message);
		echoResponse.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
		return echoResponse;
	}

}
