package org.example.exceptions;

public class FailedToUpdateProductQuantityException extends RuntimeException{
    public FailedToUpdateProductQuantityException(String message) {
        super(message);
    }
}


