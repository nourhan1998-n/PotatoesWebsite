package org.example.DAOs;

import org.example.entities.Order;

public class OrderDAO extends GenericDAOImpl<Order, Integer> {
    public OrderDAO(){
        super(Order.class);
    }
}
