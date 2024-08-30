package org.example.DAOs;

import org.example.entities.Admin;
import org.example.entities.User;

public class AdminDAO extends GenericDAOImpl<Admin, Integer> {
    public AdminDAO(){
        super(Admin.class);
    }

}
