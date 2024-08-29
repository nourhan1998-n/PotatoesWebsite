package org.example.DAOs;

import org.example.entities.Orderitem;
import org.example.entities.OrderitemId; // Make sure the OrderItem entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class OrderItemDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public OrderItemDAO(){
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    public void createOrderItem(Orderitem orderItem){
        try {
            em.getTransaction().begin();
            em.persist(orderItem);
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to add order item to the database");
        }
    }

    public Orderitem getOrderItem(OrderitemId orderItemId){
        Orderitem orderItem = null;
        try {
            em.getTransaction().begin();

            // Find the order item by ID
            orderItem = em.find(Orderitem.class, orderItemId);

            if (orderItem != null) {
                // Remove the order item from the database
                em.remove(orderItem);
                System.out.println("Order item with ID " + orderItemId + " was removed.");
            } else {
                System.out.println("Order item with ID " + orderItemId + " not found.");
            }

            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to remove order item from the database");
        }
        return orderItem;
    }

    public void updateOrderItem(Orderitem updatedOrderItem) {
        try {
            em.getTransaction().begin();

            // Find the order item by ID
            Orderitem oldOrderItem = em.find(Orderitem.class, updatedOrderItem.getId());

            if (oldOrderItem != null) {
                // Update the order item's details
                oldOrderItem.setIdorder(updatedOrderItem.getIdorder());
                oldOrderItem.setQuantity(updatedOrderItem.getQuantity());
                oldOrderItem.setPrice(updatedOrderItem.getPrice());
                oldOrderItem.setIdproduct(updatedOrderItem.getIdproduct());
                // Update other fields as needed

                // The changes are automatically tracked and updated in the database upon commit
            } else {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                System.out.println("Order item with ID " + updatedOrderItem.getId() + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to update order item in the database");
        }
    }

    public void deleteOrderItem(OrderitemId orderItemId) {
        try {
            em.getTransaction().begin();

            // Find the order item by ID
            Orderitem orderItem = em.find(Orderitem.class, orderItemId);

            if (orderItem != null) {
                // Remove the order item from the database
                em.remove(orderItem);
                System.out.println("Order item with ID " + orderItemId + " has been deleted.");
            } else {
                System.out.println("Order item with ID " + orderItemId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to delete order item in the database");
        } finally {
            em.close();
        }
    }

    public void closeDAOResources(){
        em.close();
        emf.close();
    }
}