package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Product;

import java.util.List;

public class ProductDAO extends GenericDAOImpl<Product, Integer> {

    private EntityManagerFactory emf;
    public ProductDAO(){
        super(Product.class);
        this.emf =  new EntityManagerFactorySinglton().getEntityManagerFactory();
    }
    public List<Product> findByName(String productName) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p WHERE p.name = :productName", Product.class)
                    .setParameter("productName", productName)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    public List<Product> findByCategoryId(Integer categoryId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p WHERE p.idcategory.id = :categoryId", Product.class)
                    .setParameter("categoryId", categoryId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    public List<Product> findByCategoryName(String categoryName) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p WHERE p.idcategory.name = :categoryName", Product.class)
                    .setParameter("categoryName", categoryName)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
