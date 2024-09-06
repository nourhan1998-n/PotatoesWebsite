package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.example.entities.*;
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
        EntityManager em = emf.createEntityManager();
        User user = null;
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            user = query.getSingleResult();
            logger.info("User found with email: " + email);
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "Error finding user with email: " + email, e);
        } finally {
            em.close();
        }
        return user;
    }

//    public static void main(String[] args) {
//        // Create an instance of UserDAOImpl
//        UserDAO userDAO = new UserDAO();
//
//        // Create a new User instance
//        User newUser = new User();
//        newUser.setId(3);
//        newUser.setName("John Doe");
//        newUser.setEmail("john3.doe@example.com");
//        newUser.setPassword("password123");
//        newUser.setJob("Developer");
//        newUser.setCredit("5000");
//        newUser.setCity("New York");
//        newUser.setStreet("5th Avenue");
//
//        // Save the User to the database
//        userDAO.save(newUser);
//        System.out.println("User created successfully!");
//
//        // Retrieve all Users from the database
//        List<User> allUsers = userDAO.findAll();
//
//        // Print all User details
//        System.out.println("All Users:");
//        for (User user : allUsers) {
//            System.out.println(user);
//        }
//
//
//    }
}