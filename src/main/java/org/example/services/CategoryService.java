package org.example.services;
import org.example.entities.Category;

public interface CategoryService {
    public void addCategory(Category category);
    public void removeCategory(Category category);
    public Category getCategoryByName(String categoryName);
    public Category getCategoryByID(Integer categoryID);
}
