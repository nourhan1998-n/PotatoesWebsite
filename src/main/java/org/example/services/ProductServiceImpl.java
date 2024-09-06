package org.example.services;

import org.example.DAOs.ProductDAO;
import org.example.entities.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO = new ProductDAO();
    @Override
    public Product getProductById(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> findByCategoryName(String categoryName) {
        return productDAO.findByCategoryName(categoryName);
    }
}
