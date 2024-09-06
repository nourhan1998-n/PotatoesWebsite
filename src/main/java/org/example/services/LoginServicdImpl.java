package org.example.services;

import org.example.DAOs.GenericDAO;
import org.example.entities.Person;
import org.example.entities.User;
import org.example.entities.Admin;
import org.example.DAOs.GenericDAOImpl;
import org.example.DAOs.UserDAO;
import org.example.DAOs.AdminDAO;




public class LoginServicdImpl implements LoginService{
    public Person login(String email, String password){
        User user = userFinder(email,password);
        if(user != null){
            return user;
        }
        Admin admin = adminFinder(email,password);
        if(admin != null){
            return admin;
        }
        return null;
    }

    public User userFinder(String email, String password){
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByEmail(email);
        if(user == null || user.getPassword().equals(password)){
            return null;
        }
        return user;
    }

    public Admin adminFinder(String email, String password){
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.findByEmail(email);
        if(admin == null || admin.getPassword().equals(password)){
            return null;
        }
        return admin;
    }

}
