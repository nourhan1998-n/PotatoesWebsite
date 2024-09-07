package org.example.services;

import org.example.DAOs.CategoryDAO;
import org.example.entities.Category;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    // Constructor to initialize CategoryDAO
    public CategoryServiceImpl() {
        this.categoryDAO = new CategoryDAO();
    }

    @Override
    public void addCategory(Category category) {
        categoryDAO.save(category);
    }

    @Override
    public void removeCategory(Category category) {
        categoryDAO.delete(category);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryDAO.findByName(categoryName); // Corrected method
    }

    @Override
    public Category getCategoryByID(Integer categoryID) {
        return categoryDAO.findById(categoryID); // Return the result
    }
}
