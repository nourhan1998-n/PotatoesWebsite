package org.example.DAOs;

import org.example.entities.Wishlist;

public class WishlistDAO extends GenericDAOImpl<Wishlist, Integer> {
    public WishlistDAO() {
        super(Wishlist.class);
    }
}
