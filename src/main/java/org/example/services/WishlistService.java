package org.example.services;


import org.example.entities.Wishlist;

import java.util.List;

public interface WishlistService {
    void addToWishlist(Wishlist wishlist);
    Wishlist findWishlist(Integer userId, Integer productId);
    List<Wishlist> findWishlistByUserId(Integer userId);
}

