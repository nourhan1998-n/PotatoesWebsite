package org.example.service;

import org.example.DAOs.CartItemDAO;
import org.example.DAOs.OrderDAO;
import org.example.entities.*;
import org.example.services.OrderService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private CartItemDAO cartItemDAO = new CartItemDAO();
    private OrderDAO orderDAO = new OrderDAO();

    @Override
    public void checkout(User user) {
        List<Cartitem> cartItems = cartItemDAO.findByUser(user);

        // Create new order
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setIduser(user);
        orderDAO.save(order);

        // Create order items from cart items
        for (Cartitem cartItem : cartItems) {
            Orderitem orderItem = new Orderitem();
            orderItem.setIdorder(order);
            orderItem.setIdproduct(cartItem.getIdproduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getIdproduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
            // You would save orderItem here using a DAO, similar to the above process
        }

        // Clear cart after checkout
        cartItemDAO.clearCartByUserId(user.getId());
    }
}
