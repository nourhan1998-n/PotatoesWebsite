package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.example.entities.Category;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO extends GenericDAOImpl<Category, Integer> {
    private static final Logger logger = Logger.getLogger(CategoryDAO.class.getName());
    private final EntityManagerFactory emf;

    public CategoryDAO(){
        super(Category.class);
        this.emf =  new EntityManagerFactorySinglton().getEntityManagerFactory();
    }


    public Category findByName(String categoryName) {
        EntityManager em = emf.createEntityManager();
        Category category = null;
        try {
            TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class);
            query.setParameter("name", categoryName);
            category = query.getSingleResult();
            logger.info("Category found with name: " + categoryName);
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "Error finding category with name: " + categoryName, e);
        } finally {
            em.close();
        }
        return category;
    }

}
