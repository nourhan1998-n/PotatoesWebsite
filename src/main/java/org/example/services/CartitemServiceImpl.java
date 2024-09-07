package org.example.services;


import org.example.DAOs.CartItemDAO;
import org.example.entities.Cartitem;
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
        return cartitemDAO.findByUserId(userId);
    }
    @Override
    public void increaseQuantity(Integer userId, Integer productId, Integer quantity) {
        Cartitem cartitem = cartitemDAO.findByUserIdAndProductId(userId, productId);
        if (cartitem != null) {
            cartitem.setQuantity(cartitem.getQuantity() + quantity);
            cartitemDAO.update(cartitem);
        }
    }
}
