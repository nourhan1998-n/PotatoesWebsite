package org.example.DAOs;

import org.example.entities.Admin;

public class AdminDAO extends GenericDAOImpl<Admin, Integer> {
    public AdminDAO(){
        super(Admin.class);
    }

}
