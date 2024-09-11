package org.example.services;

import org.example.entities.Cartitem;
import org.example.entities.User;

import java.util.List;

public interface CartitemService {
    void addCartItem(Cartitem cartitem);
    void updateCartItem(Cartitem cartitem);
    void removeCartItem(Cartitem cartitem);
    List<Cartitem> getCartItemsByUserId(Integer userId);
    public Cartitem findByUserIdAndProductId (Integer userId, Integer productId);
    void clearCart(Integer userId);
    void increaseQuantity(Integer userId, Integer productId, Integer quantity);
}
