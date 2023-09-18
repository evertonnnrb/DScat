package com.dscat.exceptions.handler;

import com.dscat.exceptions.DatabaseIntegrityException;
import com.dscat.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExcptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundResourceHandler(
            ResourceNotFoundException err, HttpServletRequest req) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(status.value());
        standardError.setError("Not found {handler}");
        standardError.setMessage(err.getMessage());
        standardError.setPath(req.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(DatabaseIntegrityException.class)
    public ResponseEntity<StandardError> databaseIntegrityResourceHandler(
            DatabaseIntegrityException err, HttpServletRequest req) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(status.value());
        standardError.setError("Integrity database violation {handler}");
        standardError.setMessage(err.getMessage());
        standardError.setPath(req.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> argumentValidationExceptionHandler(
            MethodArgumentNotValidException err, HttpServletRequest req) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(status.value());
        standardError.setError("Integrity database violation {handler}");
        standardError.setMessage(err.getMessage());
        standardError.setPath(req.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
}
