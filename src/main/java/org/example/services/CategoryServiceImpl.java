package org.example.services;

import org.example.entities.Category;
import org.example.DAOs.CategoryDAO;
import org.example.exceptions.alreadyexist.CategoryAlreadyExistsException;
import org.example.exceptions.notfound.CategoryNotFoundException;

public class CategoryServiceImpl  implements CategoryService{

    private final CategoryDAO categoryDAO;

    // Constructor to initialize CategoryDAO
    public CategoryServiceImpl() {
        this.categoryDAO = new CategoryDAO();
    }

    @Override
    public void addCategoryByName(String categoryName) throws CategoryAlreadyExistsException {
        if (categoryDAO.findByName(categoryName) != null) {
            throw new CategoryAlreadyExistsException("Category with name " + categoryName + " already exists.");
        }

        Category addedCategory = new Category();
        addedCategory.setName(categoryName);
        categoryDAO.save(addedCategory);
    }

    @Override
    public void removeCategoryByName(String categoryName) throws CategoryNotFoundException{
        Category removedCategory = categoryDAO.findByName(categoryName);

        if (removedCategory == null) {
            throw new CategoryNotFoundException("Category with name " + categoryName + " not found.");
        }

        categoryDAO.delete(removedCategory);
    }

    @Override
    public void removeCategoryById(Integer categoryID) throws CategoryNotFoundException{
        Category removedCategory = categoryDAO.findById(categoryID);

        if (removedCategory == null) {
            throw new CategoryNotFoundException("Category with ID " + categoryID + " not found.");
        }

        categoryDAO.delete(removedCategory);
    }

    @Override
    public Category getCategoryByName(String categoryName) throws CategoryNotFoundException{
        Category foundCategory = categoryDAO.findByName(categoryName); // Corrected method

        if (foundCategory == null) {
            throw new CategoryNotFoundException("Category with name " + categoryName + " not found.");
        }

        return foundCategory;
    }

    @Override
    public Category getCategoryByID(Integer categoryID) throws CategoryNotFoundException{
        Category foundCategory = categoryDAO.findById(categoryID); // Corrected method

        if (foundCategory == null) {
            throw new CategoryNotFoundException("Category with ID " + categoryID + " not found.");
        }

        return foundCategory;
    }

    @Override
    public void updateCategory(Category updatedCategory) throws CategoryNotFoundException{

        Category foundCategory = categoryDAO.findById(updatedCategory.getId()); // Corrected method

        if (foundCategory == null) {
            throw new CategoryNotFoundException("Category with Name " + updatedCategory.getName() + " not found.");
        }

        categoryDAO.update(updatedCategory);
    }
}
