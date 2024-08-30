package org.example.DAOs;

import org.example.entities.Orderitem;
import org.example.entities.Product;  // Make sure the Product entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ProductDAO extends GenericDAOImpl<Product, Integer> {
    public ProductDAO(){
        super(Product.class);
    }
}
