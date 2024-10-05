package org.example.services;

import org.example.entities.User;
import org.example.entities.Order;
import org.example.entities.Orderitem;
import java.util.List;

public interface OrderService {
    List<Orderitem> checkout();
    List<Order> getAllOrders();
    List<Orderitem> getAllOrdersByUserId(Integer id);
}
