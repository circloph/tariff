package com.quarkus.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
    private List<Error> errors = new ArrayList<>();

    public List<Error> getAllErrors() {
        return errors;
    }
}

