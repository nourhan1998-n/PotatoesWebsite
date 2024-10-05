package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.example.entities.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends GenericDAOImpl<User, Integer> {
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());
    private EntityManagerFactory emf;
    public UserDAO() {
        super(User.class);
        this.emf =  new EntityManagerFactorySinglton().getEntityManagerFactory();
    }
//    A TypedQuery in Java Persistence API (JPA) is a type-safe query
//    interface that allows you to execute JPQL (Java Persistence Query Language)
//    or native SQL queries while ensuring that the results are of the expected type.

    public User findByEmail(String email) {
        // Validate email input
        if (email == null || email.trim().isEmpty()) {
            logger.warning("Email is null or empty. Cannot search for user.");
            return null; // or throw new IllegalArgumentException("Email cannot be null or empty");
        }

        EntityManager em = emf.createEntityManager();
        User user = null;

        try {
            // Create a typed query to find the user by email
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);

            // Use getResultList() to avoid NoResultException and handle cases with multiple results
            List<User> users = query.getResultList();

            if (!users.isEmpty()) {
                // Assuming email is unique and there will only be one user, take the first result
                user = users.get(0);
                logger.info("User found with email: " + email);
            } else {
                logger.info("No user found with email: " + email);
            }
        } catch (PersistenceException e) {
            // Log and handle unexpected persistence-related exceptions
            logger.log(Level.SEVERE, "Persistence error occurred while finding user with email: " + email, e);
        } finally {
            // Always close the EntityManager to free resources
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return user; // Return the user if found, otherwise null
    }

}