package org.example.DAOs;

import org.example.entities.Admin;  // Make sure the Admin entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AdminDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public AdminDAO(){
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    public void createAdmin(Admin admin){
        try {
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to add admin to the database");
        }
    }

    public Admin getAdmin(Integer adminId){
        Admin admin = null;
        try {
            em.getTransaction().begin();

            // Find the admin by ID
            admin = em.find(Admin.class, adminId);

            if (admin != null) {
                // Remove the admin from the database
                em.remove(admin);
                System.out.println("Admin with ID " + adminId + " was removed.");
            } else {
                System.out.println("Admin with ID " + adminId + " not found.");
            }

            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to remove admin from the database");
        }
        return admin;
    }

    public void updateAdmin(Admin updatedAdmin) {
        try {
            em.getTransaction().begin();

            // Find the admin by ID
            Admin oldAdmin = em.find(Admin.class, updatedAdmin.getId());

            if (oldAdmin != null) {
                // Update the admin's details
                oldAdmin.setEmail(updatedAdmin.getEmail());
                oldAdmin.setPassword(updatedAdmin.getPassword());

                // The changes are automatically tracked and updated in the database upon commit
            } else {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                System.out.println("Admin with ID " + updatedAdmin.getId() + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to update admin in the database");
        }
    }

    public void deleteAdmin(Integer adminId) {
        try {
            em.getTransaction().begin();

            // Find the admin by ID
            Admin admin = em.find(Admin.class, adminId);

            if (admin != null) {
                // Remove the admin from the database
                em.remove(admin);
                System.out.println("Admin with ID " + adminId + " has been deleted.");
            } else {
                System.out.println("Admin with ID " + adminId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to delete admin in the database");
        } finally {
            em.close();
        }
    }

    public void closeDAOResources(){
        em.close();
        emf.close();
    }

}
