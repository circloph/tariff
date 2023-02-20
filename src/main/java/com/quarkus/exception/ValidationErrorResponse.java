package com.quarkus.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.ArrayList;
import java.util.List;
@RegisterForReflection
public class ValidationErrorResponse {
    private List<Error> errors = new ArrayList<>();

    public List<Error> getAllErrors() {
        return errors;
    }
}

