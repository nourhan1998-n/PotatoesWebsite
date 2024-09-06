package org.example.services;

import org.example.DAOs.GenericDAOImpl;
import org.example.DAOs.ProductDAO;
import org.example.DAOs.UserDAO;
import org.example.entities.Product;
import org.example.entities.User;
import java.util.List;

public class AdminServiceImpl implements AdminSevice{
    public User getUserByID(Integer id){
        UserDAO userDAO = new UserDAO();
        return userDAO.findById(id);
    }
    public User getUserByEmail(String email){
        UserDAO userDAO = new UserDAO();
        return userDAO.findByEmail(email);
    }

    public Product getProductByID(Integer id){
        GenericDAOImpl<Product,Integer> productDAO = new GenericDAOImpl(Product.class);
        return productDAO.findById(id);
    }
    public List<Product> getProductByName(String productName){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.findByName(productName);
    }
    public List<Product>  getProductByCategory(String categoryName){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.findByCategoryName(categoryName);
    }

}
