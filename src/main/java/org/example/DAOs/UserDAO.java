package org.example.DAOs;

import org.example.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class UserDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public UserDAO(){
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    public void createUser(User user){
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }
        catch(Exception e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to add user to the database");
        }
    }

    public User getUser(Integer userId){
        User user = null;
        try {
            em.getTransaction().begin();

            // Find the user by ID
            user = em.find(User.class, userId);

            if (user != null) {
                // Remove the user from the database
                em.remove(user);
                System.out.println("User with ID " + userId + " was removed.");
            } else {
                System.out.println("User with ID " + userId + " not found.");
            }

            em.getTransaction().commit();
        }
        catch(Exception e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to remove user to the database");
        }
        return user;
    }

    public void updateUser(User updatedUser) {
        try {
            em.getTransaction().begin();

            // Find the user by ID
            User oldUser = em.find(User.class, updatedUser.getId());

            if (oldUser != null) {
                // Update the user's details
                oldUser.setCity(updatedUser.getCity());
                oldUser.setCredit(updatedUser.getCredit());
                oldUser.setEmail(updatedUser.getEmail());
                oldUser.setName(updatedUser.getName());
                oldUser.setJob(updatedUser.getJob());
                oldUser.setStreet(updatedUser.getStreet());
                oldUser.setPassword(updatedUser.getPassword());

                // The changes are automatically tracked and updated in the database upon commit
            } else {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                System.out.println("User with ID " + updatedUser.getId() + " not found.");
            }

            em.getTransaction().commit();
        }
        catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to update user to the database");
        }
    }

    public void deleteUser(Integer userId) {

        try {
            em.getTransaction().begin();

            // Find the user by ID
            User user = em.find(User.class, userId);

            if (user != null) {
                // Remove the user from the database
                em.remove(user);
                System.out.println("User with ID " + userId + " has been deleted.");
            } else {
                System.out.println("User with ID " + userId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to delete user to the database");
        } finally {
            em.close();
        }
    }

    public void closeDAOResources(){
        em.close();
        emf.close();
    }

}
