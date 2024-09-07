package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.example.entities.Admin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAO extends GenericDAOImpl<Admin, Integer> {
    private static final Logger logger = Logger.getLogger(AdminDAO.class.getName());
    private final EntityManagerFactory emf;
    public AdminDAO(){
        super(Admin.class);
        this.emf =  new EntityManagerFactorySinglton().getEntityManagerFactory();

    }
    public Admin findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        Admin admin = null;
        try {
            TypedQuery<Admin> query = em.createQuery("SELECT a FROM Admin a WHERE a.email = :email", Admin.class);
            query.setParameter("email", email);
            admin = query.getSingleResult();
            logger.info("Admin found with email: " + email);
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "Error finding admin with email: " + email, e);
        } finally {
            em.close();
        }
        return admin;
    }
}
