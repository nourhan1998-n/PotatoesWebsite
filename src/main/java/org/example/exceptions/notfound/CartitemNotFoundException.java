package org.example.exceptions.notfound;

public class CartitemNotFoundException extends NotFoundException {
    public CartitemNotFoundException(String message) {
        super(message);
    }
}