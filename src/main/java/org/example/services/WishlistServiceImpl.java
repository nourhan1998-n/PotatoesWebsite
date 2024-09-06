package org.example.services;

import org.example.DAOs.WishlistDAO;
import org.example.entities.Wishlist;

import java.util.List;

public class WishlistServiceImpl implements WishlistService {
    private WishlistDAO wishlistDAO = new WishlistDAO ();

    @Override
    public void addToWishlist(Wishlist wishlist) {
        wishlistDAO.addProductToWishlist(wishlist);
    }

    @Override
    public Wishlist findWishlist(Integer userId, Integer productId) {
        return wishlistDAO.findByUserAndProduct(userId, productId);
    }

    @Override
    public List<Wishlist> findWishlistByUserId(Integer userId) {
        return wishlistDAO.findWishlistByUserId(userId);
    }
}
