package org.example.DAOs;

import org.example.entities.Cartitem;
import org.example.entities.Category;  // Make sure the Category entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CategoryDAO extends GenericDAOImpl<Category, Integer> {
    public CategoryDAO(){
        super(Category.class);
    }
}
