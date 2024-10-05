package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import org.example.entities.Admin;

public class AdminDAO extends GenericDAOImpl<Admin, Integer> {
    private EntityManagerFactory emf;
    public AdminDAO(){
        super(Admin.class);
        this.emf =  new EntityManagerFactorySinglton().getEntityManagerFactory();
    }
    public Admin findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Admin a WHERE a.email = :email", Admin.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

}
