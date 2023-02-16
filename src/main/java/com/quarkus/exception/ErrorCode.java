package com.quarkus.exception;

public enum ErrorCode {

    NAME_TAKEN("this name is already taken", "name"),
    NAME_BLANK("Name may not be blank", "name");

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

