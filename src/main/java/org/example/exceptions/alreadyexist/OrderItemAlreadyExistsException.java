package org.example.exceptions.alreadyexist;

public class OrderItemAlreadyExistsException extends AlreadyExistsException {
    public OrderItemAlreadyExistsException(String message) {
        super(message);
    }
}
