package com.quarkus.exception;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidExceptionHandler implements ExceptionMapper<ValidationException> {
    @Override
    public Response toResponse(ValidationException  exception) {
        String message = exception.getLocalizedMessage().split(": ")[1].trim();
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error(message,
                ErrorCode.valueOf(message).getField(), ErrorCode.valueOf(message).getMessage())).build();
    }

}
