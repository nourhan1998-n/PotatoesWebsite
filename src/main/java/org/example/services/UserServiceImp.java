package org.example.services;

import org.example.DAOs.UserDAO;
import org.example.entities.User;
import org.example.exceptions.IncorrectPasswordException;
import org.example.exceptions.alreadyexist.UserAlreadyExistsException;
import org.example.exceptions.notfound.CategoryNotFoundException;
import org.example.exceptions.notfound.UserNotFoundException;

public class UserServiceImp implements UserService {
    private UserDAO userDAO = new UserDAO();

    @Override
    public void register(User user) throws UserAlreadyExistsException {
        if(userDAO.findByEmail(user.getEmail()) != null){
            throw new UserAlreadyExistsException("User with Email " + user.getEmail() + " already exist.");
        }
        userDAO.save(user);
    }
    @Override
    public User getUserById(Integer id) throws UserNotFoundException {
        User user = userDAO.findById(id);

        if(user == null){
            throw new UserNotFoundException("User with Id " + id + " not found.");
        }
        return user;
    }

    @Override
    public User login(String email, String password) throws UserNotFoundException, IncorrectPasswordException {
        User user = userDAO.findByEmail(email);

        if(user == null){
            throw new UserNotFoundException("User with Email " + email + " not found.");
        }

        if (!user.getPassword().equals(password)) {
            throw new IncorrectPasswordException("Incorrect Password for email " + email + ".");
        }
        return user;
    }
    @Override
    public void updateUser(User user) throws UserNotFoundException{

        if (userDAO.findById(user.getId()) == null) {
            throw new UserNotFoundException("User with Email " + user.getEmail() + " not found.");
        }
        userDAO.update(user);
    }

}