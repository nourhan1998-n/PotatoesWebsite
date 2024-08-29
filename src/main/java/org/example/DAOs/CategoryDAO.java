package org.example.DAOs;

import org.example.entities.Category;  // Make sure the Category entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CategoryDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public CategoryDAO(){
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    public void createCategory(Category category){
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to add category to the database");
        }
    }

    public Category getCategory(Integer categoryId){
        Category category = null;
        try {
            em.getTransaction().begin();

            // Find the category by ID
            category = em.find(Category.class, categoryId);

            if (category != null) {
                // Remove the category from the database
                em.remove(category);
                System.out.println("Category with ID " + categoryId + " was removed.");
            } else {
                System.out.println("Category with ID " + categoryId + " not found.");
            }

            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to remove category from the database");
        }
        return category;
    }

    public void updateCategory(Category updatedCategory) {
        try {
            em.getTransaction().begin();

            // Find the category by ID
            Category oldCategory = em.find(Category.class, updatedCategory.getId());

            if (oldCategory != null) {
                // Update the category's details
                oldCategory.setName(updatedCategory.getName());
                // Update other fields as needed

                // The changes are automatically tracked and updated in the database upon commit
            } else {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                System.out.println("Category with ID " + updatedCategory.getId() + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to update category in the database");
        }
    }

    public void deleteCategory(Integer categoryId) {
        try {
            em.getTransaction().begin();

            // Find the category by ID
            Category category = em.find(Category.class, categoryId);

            if (category != null) {
                // Remove the category from the database
                em.remove(category);
                System.out.println("Category with ID " + categoryId + " has been deleted.");
            } else {
                System.out.println("Category with ID " + categoryId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to delete category in the database");
        } finally {
            em.close();
        }
    }

    public void closeDAOResources(){
        em.close();
        emf.close();
    }
}
