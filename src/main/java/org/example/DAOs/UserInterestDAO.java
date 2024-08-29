package org.example.DAOs;

import org.example.entities.Userinterest;  // Make sure the UserInterest entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.UserinterestId;

public class UserInterestDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public UserInterestDAO(){
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    public void createUserInterest(Userinterest userInterest){
        try {
            em.getTransaction().begin();
            em.persist(userInterest);
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to add user interest to the database");
        }
    }

    public Userinterest getUserInterest(UserinterestId userInterestId){
        Userinterest userInterest = null;
        try {
            em.getTransaction().begin();

            // Find the user interest by ID
            userInterest = em.find(Userinterest.class, userInterestId);

            if (userInterest != null) {
                // Remove the user interest from the database
                em.remove(userInterest);
                System.out.println("User interest with ID " + userInterestId + " was removed.");
            } else {
                System.out.println("User interest with ID " + userInterestId + " not found.");
            }

            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to remove user interest from the database");
        }
        return userInterest;
    }

    public void updateUserInterest(Userinterest updatedUserInterest) {
        try {
            em.getTransaction().begin();

            // Find the user interest by ID
            Userinterest oldUserInterest = em.find(Userinterest.class, updatedUserInterest.getId());

            if (oldUserInterest != null) {
                // Update the user interest's details
                oldUserInterest.setIdcategory(updatedUserInterest.getIdcategory());
                oldUserInterest.setIduser(updatedUserInterest.getIduser());
                // Update other fields as needed

                // The changes are automatically tracked and updated in the database upon commit
            } else {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                System.out.println("User interest with ID " + updatedUserInterest.getId() + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to update user interest in the database");
        }
    }

    public void deleteUserInterest(UserinterestId userInterestId) {
        try {
            em.getTransaction().begin();

            // Find the user interest by ID
            Userinterest userInterest = em.find(Userinterest.class, userInterestId);

            if (userInterest != null) {
                // Remove the user interest from the database
                em.remove(userInterest);
                System.out.println("User interest with ID " + userInterestId + " has been deleted.");
            } else {
                System.out.println("User interest with ID " + userInterestId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to delete user interest in the database");
        } finally {
            em.close();
        }
    }

    public void closeDAOResources(){
        em.close();
        emf.close();
    }
}
