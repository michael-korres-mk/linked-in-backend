package com.licnosoft.linkedin.config.exceptions;

public class AppValidationException extends RuntimeException {

    private String response;

    public AppValidationException() {
        super("Απουσιάζουν υποχρεωτικά πεδία");
    }

    public AppValidationException(String message) {
        super(message);
    }

    public AppValidationException(String message, String response) {
        super(message);
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
