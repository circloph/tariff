package com.quarkus.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<CustomValidationException>  {
    @Override
    public Response toResponse(CustomValidationException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getError()).build();
    }

}
