package com.licnosoft.linkedin.config.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorResponse {

    private HttpStatus status;
    private int statusCode;
    private String message;
    private List<String> errors;

    public ErrorResponse() {}

    public ErrorResponse(HttpStatus status, int statusCode, String message) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, int statusCode, String message, List<String> errors) {
        this(status, statusCode, message);
        this.errors = errors;
    }

    public ErrorResponse(HttpStatus status, int statusCode, List<String> errors) {
        this.status = status;
        this.statusCode = statusCode;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
