package com.code73.function.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.code73.function.response.dto.ErrorResponse;

@Provider
public class AppExceptionServiceMapper implements ExceptionMapper<Exception>{

	@Override
	public Response toResponse(Exception exception) {
		com.code73.function.response.dto.ErrorResponse error = new ErrorResponse();
		error.setException(exception.getClass().getName());
		error.setMessage(exception.getMessage() == null ? "Error desconocido":exception.getMessage());
		error.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		
		Response response = Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(error)
				.type(MediaType.APPLICATION_JSON)
				.build();
		return response;
	}

}
