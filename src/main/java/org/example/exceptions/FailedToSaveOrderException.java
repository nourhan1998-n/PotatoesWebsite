package org.example.exceptions;

public class FailedToSaveOrderException extends RuntimeException{
    public FailedToSaveOrderException(String message) {
        super(message);
    }
}


