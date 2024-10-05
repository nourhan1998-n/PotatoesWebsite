package org.example.services;

import org.example.entities.Cartitem;
import java.util.List;

public interface CartitemService {
    List<Cartitem> addCartItem(Cartitem cartitem);
    void updateCartItem(Cartitem cartitem);
    void removeCartItem(Cartitem cartitem);
    List<Cartitem> getCartItemsByUserId();
    void increaseQuantity(Integer productId, Integer quantity);
    void updateCartItemFromSession();
}
