package com.code73.function.response;

public interface PersonEchoResponse {
	
	public EchoResponse created(String username);

	public EchoResponse notFound(String username);

	public EchoResponse updated(String username);

	public EchoResponse deleted(String username);

}
