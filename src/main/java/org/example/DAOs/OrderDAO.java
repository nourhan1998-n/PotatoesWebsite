package org.example.DAOs;

import org.example.entities.Category;
import org.example.entities.Order;  // Make sure the Order entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class OrderDAO extends GenericDAOImpl<Order, Integer> {
    public OrderDAO(){
        super(Order.class);
    }
}
