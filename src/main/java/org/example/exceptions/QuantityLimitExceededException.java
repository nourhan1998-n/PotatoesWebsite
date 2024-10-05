package org.example.exceptions;

public class QuantityLimitExceededException extends RuntimeException{
    public QuantityLimitExceededException(String message) {
        super(message);
    }
}


