package org.example.services;

import org.example.entities.User;
import org.example.entities.Product;
import java.util.List;


public interface AdminSevice {

    public User getUserByID(Integer id);
    public User getUserByEmail(String email);

    public Product getProductByID(Integer id);
    public List<Product>  getProductByName(String productName);
    public List<Product>  getProductByCategory(String productName);
}
