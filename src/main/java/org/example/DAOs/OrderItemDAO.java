package org.example.DAOs;

import org.example.entities.Orderitem;

public class OrderItemDAO extends GenericDAOImpl<Orderitem, Integer> {
    public OrderItemDAO(){
        super(Orderitem.class);
    }
}