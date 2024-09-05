package org.example.DAOs;

import org.example.entities.Product;

public class ProductDAO extends GenericDAOImpl<Product, Integer> {
    public ProductDAO(){
        super(Product.class);
    }
}
