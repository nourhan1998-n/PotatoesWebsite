package org.example.services;
import org.example.entities.Category;

public interface CategoryService {
    public void addCategoryByName(String categoryName);
    public void removeCategoryByName(String categoryName);
    public void removeCategoryById(Integer categoryID);
    public Category getCategoryByName(String categoryName);
    public Category getCategoryByID(Integer categoryID);
    public void updateCategory(Category updatedCategory);
}
