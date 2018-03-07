package com.code73.function.response;

public interface JWTEchoResponse {
	
	public EchoResponse unAuthorized(String jwt);

}
