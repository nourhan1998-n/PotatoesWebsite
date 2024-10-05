package org.example.services;

import org.example.DAOs.AdminDAO;
import org.example.DAOs.ProductDAO;
import org.example.DAOs.UserDAO;
import org.example.entities.Admin;
import org.example.entities.Product;
import org.example.entities.User;
import org.example.exceptions.alreadyexist.ProductAlreadyExistsException;
import org.example.exceptions.notfound.ProductNotFoundException;
import org.example.exceptions.notfound.UserNotFoundException;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final ProductDAO productDAO;
    private final UserDAO userDAO;
    private final AdminDAO adminDAO= new AdminDAO();

    // Constructor to initialize DAOs
    public AdminServiceImpl() {
        this.productDAO = new ProductDAO();
        this.userDAO = new UserDAO();
    }
    @Override
    public Admin login(String email, String password) {
        Admin admin = adminDAO.findByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
    @Override
    public void addProduct(Product product) throws ProductAlreadyExistsException {
        if (!productDAO.findExactProductByName(product.getName()).isEmpty()) {
            throw new ProductAlreadyExistsException("Product with name " + product.getName() + " already exists.");
        }
        productDAO.save(product);
    }

    @Override
    public User getUserByID(Integer id) throws UserNotFoundException {
        User foundUser = userDAO.findById(id);
        if (foundUser == null) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
        return foundUser;
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        User foundUser = userDAO.findByEmail(email);
        if (foundUser == null) {
            throw new UserNotFoundException("User with email " + email + " not found.");
        }
        return foundUser;
    }

    @Override
    public Product getProductByID(Integer id) throws ProductNotFoundException {
        Product foundProduct = productDAO.findById(id);
        if (foundProduct == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        return foundProduct;
    }

    @Override
    public List<Product> getProductByName(String productName) {
        return  productDAO.findByProductName(productName);

    }

    @Override
    public List<Product> getProductByCategory(String categoryName) {
        return productDAO.findByCategoryName(categoryName);
    }

    @Override
    public void updateProduct(Product product) throws ProductNotFoundException {
        Product foundProduct = productDAO.findById(product.getId());
        if (foundProduct == null) {
            throw new ProductNotFoundException("Product with ID " + product.getId() + " not found.");
        }
        productDAO.update(product);
    }

    @Override
    public void removeProductById(Integer productId) throws ProductNotFoundException {
        Product foundProduct = productDAO.findById(productId);
        if (foundProduct == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }
        productDAO.delete(foundProduct);
    }

    @Override
    public void removeProductByName(String productName) throws ProductNotFoundException {
        List<Product> foundProducts = productDAO.findExactProductByName(productName);
        if (foundProducts.isEmpty()) {
            throw new ProductNotFoundException("Product with name " + productName + " not found.");
        }
        productDAO.delete(foundProducts.get(0));
    }
}
