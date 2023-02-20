package com.quarkus.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public enum ErrorCode {

    NAME_TAKEN("this name is already taken", "name"),
    NAME_BLANK("Name may not be blank", "name"),
    TOO_LONG_NAME("Size of name must be between 0 and 128", "name"),
    REQUIRED_FIELD("The field is required", ""),
    INVALID_TARIFF_ID("Invalid tariff id", "id"),
    INVALID_PACKAGE_ID("Invalid package id", "id");

    private String message;
    private String field;

    ErrorCode(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }
}

