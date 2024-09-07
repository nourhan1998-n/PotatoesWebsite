package org.example.services;

import org.example.DAOs.ProductDAO;
import org.example.DAOs.UserDAO;
import org.example.entities.Product;
import org.example.entities.User;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final UserDAO userDAO;
    private final ProductDAO productDAO;

    // Constructor-based Dependency Injection
    public AdminServiceImpl() {
        this.userDAO = new UserDAO();
        this.productDAO = new ProductDAO();
    }

    @Override
    public User getUserByID(Integer id) {
        return userDAO.findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public Product getProductByID(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> getProductByName(String productName) {
        return productDAO.findByName(productName);
    }

    @Override
    public List<Product> getProductByCategory(String categoryName) {
        return productDAO.findByCategoryName(categoryName);
    }
}
