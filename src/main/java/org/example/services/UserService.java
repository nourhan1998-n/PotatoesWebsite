package org.example.services;

import org.example.entities.User;

public interface UserService {
    void register(User user);
    User login(String email, String password);
    public boolean authenticate(String email, String password);
}
