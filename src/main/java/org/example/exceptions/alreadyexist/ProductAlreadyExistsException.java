package org.example.exceptions.alreadyexist;

public class ProductAlreadyExistsException extends AlreadyExistsException {
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
