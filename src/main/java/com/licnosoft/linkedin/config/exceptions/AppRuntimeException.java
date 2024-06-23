package com.licnosoft.linkedin.config.exceptions;

public class AppRuntimeException extends RuntimeException {

    public AppRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppRuntimeException(String message) {
        super(message);
    }

    public AppRuntimeException(Throwable cause) {
        super(cause);
    }
}
