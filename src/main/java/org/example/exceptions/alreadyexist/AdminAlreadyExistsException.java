package org.example.exceptions.alreadyexist;

public class AdminAlreadyExistsException extends AlreadyExistsException {
    public AdminAlreadyExistsException(String message) {
        super(message);
    }
}
