package org.example.DAOs;

import org.example.entities.*;

import java.util.List;

public class UserDAO extends GenericDAOImpl<User, Integer> {
    public UserDAO() {
        super(User.class);
    }
    public static void main(String[] args) {
        // Create an instance of UserDAOImpl
        UserDAO userDAO = new UserDAO();

        // Create a new User instance
        User newUser = new User();
        newUser.setId(3);
        newUser.setName("John Doe");
        newUser.setEmail("john3.doe@example.com");
        newUser.setPassword("password123");
        newUser.setJob("Developer");
        newUser.setCredit("5000");
        newUser.setCity("New York");
        newUser.setStreet("5th Avenue");

        // Save the User to the database
        userDAO.save(newUser);
        System.out.println("User created successfully!");

        // Retrieve all Users from the database
        List<User> allUsers = userDAO.findAll();

        // Print all User details
        System.out.println("All Users:");
        for (User user : allUsers) {
            System.out.println(user);
        }


    }
}