package org.example.services;

import org.example.entities.User;

public interface UserService {
    void register(User user);
    User getUserById(Integer id);
    User login(String email, String password);
    public void updateUser(User user);
}
