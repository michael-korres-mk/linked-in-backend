package com.licnosoft.linkedin.config.exceptions;

public class AppNotFoundException extends RuntimeException {

    public AppNotFoundException() {
        super("ENTITY_NOT_FOUND");
    }

    public AppNotFoundException(String message) {
        super(message);
    }
}
