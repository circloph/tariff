package com.quarkus.exception;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        exception.getConstraintViolations().stream().forEach(field ->
                validationErrorResponse.getAllErrors().add(
                        new Error(field.getMessage(), field.getPropertyPath().toString().split("\\.")[2], ErrorCode.valueOf(field.getMessage()).getMessage())));
        return Response.status(Response.Status.BAD_REQUEST).entity(validationErrorResponse).build();
    }

}
