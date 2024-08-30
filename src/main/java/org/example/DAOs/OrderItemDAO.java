package org.example.DAOs;

import org.example.entities.Order;
import org.example.entities.Orderitem;
import org.example.entities.OrderitemId; // Make sure the OrderItem entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class OrderItemDAO extends GenericDAOImpl<Orderitem, Integer> {
    public OrderItemDAO(){
        super(Orderitem.class);
    }
}