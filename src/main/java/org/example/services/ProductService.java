package org.example.services;


import org.example.entities.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Integer id);
    List<Product> getAllProducts();
    List<Product> findByCategoryName(String categoryName);
    List<Product> findByProductName(String categoryName);
    void addProduct(Product product);
    Product updateProduct(Product product);
    void deleteProductByName(String productName);
    void deleteProductById(Integer id);
}

