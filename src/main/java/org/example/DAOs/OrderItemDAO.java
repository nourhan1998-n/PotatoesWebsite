package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.entities.Orderitem;

import java.util.List;
import java.util.ArrayList;

import java.util.logging.Logger;

public class OrderItemDAO extends GenericDAOImpl<Orderitem, Integer> {
    private static final Logger logger = Logger.getLogger(OrderItemDAO.class.getName());
    private EntityManagerFactory emf;

    public OrderItemDAO() {
        super(Orderitem.class);
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();
    }

    public List<Orderitem> findAllByUserId(Integer userId) {
        EntityManager em = emf.createEntityManager();
        List<Orderitem> orderItems = null;
        try {
            TypedQuery<Orderitem> query = em.createQuery(
                    "SELECT oi FROM Orderitem oi JOIN oi.idorder o WHERE o.iduser.id = :userId", Orderitem.class);
            query.setParameter("userId", userId);
            orderItems = query.getResultList();
        } catch (Exception e) {
            logger.severe("Error finding order items for user ID " + userId + ": " + e.getMessage());
        } finally {
            em.close();
        }
        return orderItems;
    }
    public List<Orderitem> findAllByHighestOrderId() {
        EntityManager em = emf.createEntityManager();
        List<Orderitem> orderItems = new ArrayList<>();
        try {
            // Step 1: Find the highest order ID
            TypedQuery<Integer> maxOrderIdQuery = em.createQuery(
                    "SELECT MAX(o.id) FROM Order o", Integer.class);
            Integer highestOrderId = maxOrderIdQuery.getSingleResult();

            // Step 2: Retrieve order items for the highest order ID
            if (highestOrderId != null) {
                TypedQuery<Orderitem> query = em.createQuery(
                        "SELECT oi FROM Orderitem oi WHERE oi.idorder.id = :orderId", Orderitem.class);
                query.setParameter("orderId", highestOrderId);
                orderItems = query.getResultList();
            }
        } catch (Exception e) {
            logger.severe("Error finding order items for the highest order ID: " + e.getMessage());
        } finally {
            em.close();
        }
        return orderItems;
    }


}
