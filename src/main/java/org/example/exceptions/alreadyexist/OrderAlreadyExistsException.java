package org.example.exceptions.alreadyexist;

public class OrderAlreadyExistsException extends AlreadyExistsException {
    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}
