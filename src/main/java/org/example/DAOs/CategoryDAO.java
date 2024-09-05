package org.example.DAOs;

import org.example.entities.Category;

public class CategoryDAO extends GenericDAOImpl<Category, Integer> {
    public CategoryDAO(){
        super(Category.class);
    }
}
