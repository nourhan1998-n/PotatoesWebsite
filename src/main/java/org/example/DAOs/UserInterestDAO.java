package org.example.DAOs;

import org.example.entities.Userinterest;

public class UserInterestDAO extends GenericDAOImpl<Userinterest, Integer> {
    public UserInterestDAO() {
        super(Userinterest.class);
    }
}
