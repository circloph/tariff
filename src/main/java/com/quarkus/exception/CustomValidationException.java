package com.quarkus.exception;

import java.io.Serializable;

public class CustomValidationException extends Exception implements Serializable {
    private Error error;

    public CustomValidationException(ErrorCode errorCode) {
        error = new Error(errorCode.toString(), errorCode.getField(), errorCode.getMessage());
    }

    public Error getError() {
        return error;
    }
}
