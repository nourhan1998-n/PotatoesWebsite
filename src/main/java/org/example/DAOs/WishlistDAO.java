package org.example.DAOs;

import org.example.entities.Userinterest;
import org.example.entities.Wishlist;  // Make sure the Wishlist entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.WishlistId;

public class WishlistDAO extends GenericDAOImpl<Wishlist, Integer> {
    public WishlistDAO() {
        super(Wishlist.class);
    }
}
