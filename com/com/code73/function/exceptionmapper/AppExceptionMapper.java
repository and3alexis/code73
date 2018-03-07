package com.code73.function.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.code73.function.response.dto.ErrorResponse;

@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException>{

	@Override
	public Response toResponse(AppException exception) {
		com.code73.function.response.dto.ErrorResponse error = new ErrorResponse();
		error.setException(exception.getCause().getClass().getName());
		error.setMessage(exception.getMessage() == null ? "Error desconocido":exception.getMessage());
		error.setStatus(430);
		
		Response response = Response
				.status(430)
				.entity(error)
				.type(MediaType.APPLICATION_JSON)
				.build();
		return response;
	}

}
