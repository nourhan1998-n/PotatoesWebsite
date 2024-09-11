package org.example.services;


import org.example.DAOs.CartItemDAO;
import org.example.entities.Cartitem;
import org.example.entities.User;

import java.util.List;

public class CartitemServiceImpl implements CartitemService {

    private CartItemDAO cartitemDAO = new CartItemDAO();

    @Override
    public void addCartItem(Cartitem cartitem) {
        cartitemDAO.save(cartitem);
    }

    @Override
    public void updateCartItem(Cartitem cartitem) {
        cartitemDAO.update(cartitem);
    }

    @Override
    public void removeCartItem(Cartitem cartitem) {
        cartitemDAO.delete(cartitem);
    }

    @Override
    public List<Cartitem> getCartItemsByUserId(Integer userId) {
        return List.of();
    }

    public List<Cartitem> getCartItemsByUserId(User user) {
        return cartitemDAO.findByUser(user);
    }

    @Override
    public Cartitem findByUserIdAndProductId (Integer userId, Integer productId){
        return cartitemDAO.findByUserIdAndProductId(userId, productId);
    }
    @Override
    public void increaseQuantity(Integer userId, Integer productId, Integer quantity) {
        Cartitem cartitem = cartitemDAO.findByUserIdAndProductId(userId, productId);
        if (cartitem != null) {
            cartitem.setQuantity(cartitem.getQuantity() + quantity);
            cartitemDAO.update(cartitem);
        }
    }
    @Override
    public void clearCart(Integer userId) {
        cartitemDAO.clearCartByUserId(userId);
    }
}
