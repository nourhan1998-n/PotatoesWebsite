package org.example.services;

import org.example.DAOs.UserDAO;
import org.example.entities.User;

public class UserServiceImp implements UserService {
    private UserDAO userDAO = new UserDAO();

    @Override
    public void register(User user) {
        userDAO.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    @Override
    public boolean authenticate(String email, String password) {
        User user = userDAO.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

}