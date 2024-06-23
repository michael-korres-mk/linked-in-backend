package com.licnosoft.linkedin.config.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception ex) {
        ErrorResponse apiError = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), Collections.singletonList(ex.getMessage()));
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value(), Collections.singletonList(ex.getMessage())), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AppNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(AppNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "MISSING_REQUIRED_FIELDS", ex.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).toList()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ErrorResponse>  handleConstraintViolation(TransactionSystemException ex) {
        Throwable cause = ex.getRootCause();
        if (cause instanceof ConstraintViolationException e) {
            return handleConstraintViolation(e);
        }
        return handleInternalServerError(ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse>  handleConstraintViolation(ConstraintViolationException ex) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "MISSING_REQUIRED_FIELDS", ex.getConstraintViolations().stream().map(error -> error.getMessage()).toList()), HttpStatus.BAD_REQUEST);
    }
}
