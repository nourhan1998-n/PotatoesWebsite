package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Wishlist;
import org.example.entities.WishlistId;

import java.util.List;

public class WishlistDAO extends GenericDAOImpl<Wishlist, Integer> {
    private EntityManagerFactory emf;
    public WishlistDAO() {
        super(Wishlist.class);
        this.emf =  new EntityManagerFactorySinglton().getEntityManagerFactory();
    }
    public void addProductToWishlist(Wishlist wishlist) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(wishlist);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Wishlist findByUserAndProduct(Integer userId, Integer productId) {
        EntityManager em = emf.createEntityManager();
        try {
            WishlistId wishlistId = new WishlistId();
            wishlistId.setIduser(userId);
            wishlistId.setIdproduct(productId);
            return em.find(Wishlist.class, wishlistId);
        } finally {
            em.close();
        }
    }

    public List<Wishlist> findWishlistByUserId(Integer userId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT w FROM Wishlist w WHERE w.iduser.id = :userId", Wishlist.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
