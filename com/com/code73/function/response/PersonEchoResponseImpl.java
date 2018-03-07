package com.code73.function.response;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.code73.function.messages.Messages;
import com.code73.function.person.KeyMessage;

@Component
public class PersonEchoResponseImpl implements PersonEchoResponse {

	@Autowired
	private Messages messages;

	public EchoResponse created(String username) {
		String messageLocation = this.messages.getString(KeyMessage.PERSON_ATTIBUTE_LOCATION, new Object[]{username});
		String message = this.messages.getString(KeyMessage.PERSON_ATTIBUTE_MESSAGE_VALUE_1, new Object[]{username});
		
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setLocation(messageLocation);
		echoResponse.setMessage(message);
		echoResponse.setStatus(Response.Status.CREATED.getStatusCode());
		return echoResponse;
	}

	public EchoResponse notFound(String username) {
		String message = this.messages.getString(KeyMessage.PERSON_ATTIBUTE_MESSAGE_VALUE_2, new Object[]{username});
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(message);
		echoResponse.setStatus(Response.Status.NOT_FOUND.getStatusCode());
		return echoResponse;
	}

	public EchoResponse updated(String username) {
		String messageLocation = this.messages.getString(KeyMessage.PERSON_ATTIBUTE_LOCATION, new Object[]{username});
		String message = this.messages.getString(KeyMessage.PERSON_ATTIBUTE_MESSAGE_VALUE_3, new Object[]{username});
		
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setLocation(messageLocation);
		echoResponse.setMessage(message);
		echoResponse.setStatus(Response.Status.CREATED.getStatusCode());
		return echoResponse;
	}

	public EchoResponse deleted(String username) {
		String message = this.messages.getString(KeyMessage.PERSON_ATTIBUTE_MESSAGE_VALUE_4, new Object[]{username});
		EchoResponse echoResponse = new EchoResponse();
		echoResponse.setMessage(message);
		echoResponse.setStatus(Response.Status.NO_CONTENT.getStatusCode());
		return echoResponse;
	}

}
