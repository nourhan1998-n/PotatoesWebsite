package org.example.services;

import org.example.DAOs.CategoryDAO;
import org.example.entities.Category;
import org.example.entities.CategoryEnum;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    // Constructor to initialize CategoryDAO
    public CategoryServiceImpl() {
        this.categoryDAO = new CategoryDAO();
    }

    @Override
    public void addCategoryByName(String categoryName) {
        Category category = new Category();
        CategoryEnum categoryEnum = CategoryEnum.valueOf(categoryName);
        category.setName(categoryEnum);
        categoryDAO.save(category);
    }
    @Override
    public void removeCategoryByName(String categoryName){
        categoryDAO.delete(getCategoryByName(categoryName));
    }
    @Override
    public void removeCategoryById(Integer categoryID){
        categoryDAO.delete(getCategoryByID(categoryID));
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryDAO.findByName(categoryName); // Corrected method
    }
    public Category getCategoryByID(Integer categoryID){
        return categoryDAO.findById(categoryID);
    }


}
