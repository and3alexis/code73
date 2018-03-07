package com.code73.function.response;

import javax.ws.rs.core.Response;

public interface AppResponse {
	
	Response generatePostResponse(Object object, String type);
	
	Response generatePutResponse(Object object, String type);
	
	Response generateGetResponse(Object object, String type);
	
	Response generateDeleteResponse(Object object, String type);

	Response generateGetNotFoundResponse(Object object, String type);

	Response generateUnAuthorizedResponse(Object object, String type);

}
