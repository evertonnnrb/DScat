package com.dscat.exceptions;

public class DatabaseIntegrityException extends RuntimeException{

    public DatabaseIntegrityException(String message) {
        super(message);
    }
}
