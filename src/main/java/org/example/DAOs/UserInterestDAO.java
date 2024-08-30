package org.example.DAOs;

import org.example.entities.Userinterest;
import org.example.entities.Userinterest;  // Make sure the UserInterest entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.UserinterestId;

public class UserInterestDAO extends GenericDAOImpl<Userinterest, Integer> {
    public UserInterestDAO() {
        super(Userinterest.class);
    }
}
