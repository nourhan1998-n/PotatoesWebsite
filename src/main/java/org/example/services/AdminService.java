package org.example.services;

import org.example.entities.Admin;
import org.example.entities.User;
import org.example.entities.Product;
import java.util.List;


public interface AdminService {

    public void addProduct(Product product);

    public User getUserByID(Integer id);
    public User getUserByEmail(String email);

    public Product getProductByID(Integer id);
    public List<Product>  getProductByName(String productName);
    public List<Product>  getProductByCategory(String productName);
    public Admin login(String email, String password);
    public void updateProduct(Product product);

    public void removeProductById(Integer productId);

    public void removeProductByName(String productName);
}
