package org.example.DAOs;

import org.example.entities.Product;  // Make sure the Product entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ProductDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public ProductDAO(){
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    public void createProduct(Product product){
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to add product to the database");
        }
    }

    public Product getProduct(Integer productId){
        Product product = null;
        try {
            em.getTransaction().begin();

            // Find the product by ID
            product = em.find(Product.class, productId);

            if (product != null) {
                // Remove the product from the database
                em.remove(product);
                System.out.println("Product with ID " + productId + " was removed.");
            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }

            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to remove product from the database");
        }
        return product;
    }

    public void updateProduct(Product updatedProduct) {
        try {
            em.getTransaction().begin();

            // Find the product by ID
            Product oldProduct = em.find(Product.class, updatedProduct.getId());

            if (oldProduct != null) {
                // Update the product's details
                oldProduct.setName(updatedProduct.getName());
                oldProduct.setDescription(updatedProduct.getDescription());
                oldProduct.setPrice(updatedProduct.getPrice());
                oldProduct.setImg(updatedProduct.getImg());
                oldProduct.setCc(updatedProduct.getCc());
                oldProduct.setColor(updatedProduct.getColor());
                oldProduct.setIdcategory(updatedProduct.getIdcategory());
                oldProduct.setQuantity(updatedProduct.getQuantity());
                oldProduct.setModel(updatedProduct.getModel());
                // Update other fields as needed

                // The changes are automatically tracked and updated in the database upon commit
            } else {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                System.out.println("Product with ID " + updatedProduct.getId() + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to update product in the database");
        }
    }

    public void deleteProduct(Integer productId) {
        try {
            em.getTransaction().begin();

            // Find the product by ID
            Product product = em.find(Product.class, productId);

            if (product != null) {
                // Remove the product from the database
                em.remove(product);
                System.out.println("Product with ID " + productId + " has been deleted.");
            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to delete product in the database");
        } finally {
            em.close();
        }
    }

    public void closeDAOResources(){
        em.close();
        emf.close();
    }
}
