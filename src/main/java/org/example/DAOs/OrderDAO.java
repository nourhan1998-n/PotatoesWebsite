package org.example.DAOs;

import org.example.entities.Order;  // Make sure the Order entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class OrderDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public OrderDAO(){
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    public void createOrder(Order order){
        try {
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to add order to the database");
        }
    }

    public Order getOrder(Integer orderId){
        Order order = null;
        try {
            em.getTransaction().begin();

            // Find the order by ID
            order = em.find(Order.class, orderId);

            if (order != null) {
                // Remove the order from the database
                em.remove(order);
                System.out.println("Order with ID " + orderId + " was removed.");
            } else {
                System.out.println("Order with ID " + orderId + " not found.");
            }

            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to remove order from the database");
        }
        return order;
    }

    public void updateOrder(Order updatedOrder) {
        try {
            em.getTransaction().begin();

            // Find the order by ID
            Order oldOrder = em.find(Order.class, updatedOrder.getId());

            if (oldOrder != null) {
                // Update the order's details
                oldOrder.setDate(updatedOrder.getDate());
                oldOrder.setIduser(updatedOrder.getIduser());
                // Update other fields as needed

                // The changes are automatically tracked and updated in the database upon commit
            } else {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                System.out.println("Order with ID " + updatedOrder.getId() + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to update order in the database");
        }
    }

    public void deleteOrder(Integer orderId) {
        try {
            em.getTransaction().begin();

            // Find the order by ID
            Order order = em.find(Order.class, orderId);

            if (order != null) {
                // Remove the order from the database
                em.remove(order);
                System.out.println("Order with ID " + orderId + " has been deleted.");
            } else {
                System.out.println("Order with ID " + orderId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to delete order in the database");
        } finally {
            em.close();
        }
    }

    public void closeDAOResources(){
        em.close();
        emf.close();
    }
}
