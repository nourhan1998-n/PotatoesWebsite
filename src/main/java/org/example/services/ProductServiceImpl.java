package org.example.services;

import org.example.DAOs.ProductDAO;
import org.example.entities.Product;
import org.example.exceptions.alreadyexist.ProductAlreadyExistsException;
import org.example.exceptions.notfound.ProductNotFoundException;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    // Constructor to initialize ProductDAO
    public ProductServiceImpl() {
        this.productDAO = new ProductDAO();
    }

    @Override
    public Product getProductById(Integer id) throws ProductNotFoundException {
        Product product = productDAO.findById(id);

        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> findByCategoryName(String categoryName) {
        return productDAO.findByCategoryName(categoryName);
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return productDAO.findByProductName(productName);
    }

    @Override
    public void addProduct(Product product) throws ProductAlreadyExistsException {
        if (!productDAO.findExactProductByName(product.getName()).isEmpty()) {
            throw new ProductAlreadyExistsException("Product with name " + product.getName() + " already exists.");
        }
        productDAO.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        Product existingProduct = productDAO.findById(product.getId());

        if (existingProduct == null) {
            throw new ProductNotFoundException("Product with ID " + product.getId() + " not found.");
        }
        return productDAO.update(product);
    }

    @Override
    public void deleteProductByName(String productName) throws ProductNotFoundException {
        List<Product> products = productDAO.findExactProductByName(productName);

        if (products.isEmpty()) {
            throw new ProductNotFoundException("Product with name " + productName + " not found.");
        }

        productDAO.delete(products.get(0));
    }

    @Override
    public void deleteProductById(Integer id) throws ProductNotFoundException {
        Product deletedProduct = productDAO.findById(id);

        if (deletedProduct == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }

        productDAO.delete(deletedProduct);
    }
}































