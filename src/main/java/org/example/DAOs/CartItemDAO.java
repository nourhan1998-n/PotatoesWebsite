package org.example.DAOs;

import org.example.entities.Cartitem;
import org.example.entities.CartitemID;  // Make sure the CartItem entity is properly imported
// Make sure the CartItem entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.CartitemId;

public class CartItemDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public CartItemDAO(){
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    public void createCartItem(Cartitem cartItem){
        try {
            em.getTransaction().begin();
            em.persist(cartItem);
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to add cart item to the database");
        }
    }

    public Cartitem getCartItem(CartitemId cartItemId){
        Cartitem cartItem = null;
        try {
            em.getTransaction().begin();

            // Find the cart item by ID
            cartItem = em.find(Cartitem.class, cartItemId);

            if (cartItem != null) {
                // Remove the cart item from the database
                em.remove(cartItem);
                System.out.println("Cart item with ID " + cartItemId + " was removed.");
            } else {
                System.out.println("Cart item with ID " + cartItemId + " not found.");
            }

            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to remove cart item from the database");
        }
        return cartItem;
    }

    public void updateCartItem(Cartitem updatedCartItem) {
        try {
            em.getTransaction().begin();

            // Find the cart item by ID
            Cartitem oldCartItem = em.find(Cartitem.class, updatedCartItem.getId());

            if (oldCartItem != null) {
                // Update the cart item's details
                oldCartItem.setIdproduct(updatedCartItem.getIdproduct());
                oldCartItem.setIduser(updatedCartItem.getIduser());
                oldCartItem.setQuantity(updatedCartItem.getQuantity());
                // Update other fields as needed

                // The changes are automatically tracked and updated in the database upon commit
            } else {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                System.out.println("Cart item with ID " + updatedCartItem.getId() + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to update cart item in the database");
        }
    }

    public void deleteCartItem(CartitemId cartItemId) {
        try {
            em.getTransaction().begin();

            // Find the cart item by ID
            Cartitem cartItem = em.find(Cartitem.class, cartItemId);

            if (cartItem != null) {
                // Remove the cart item from the database
                em.remove(cartItem);
                System.out.println("Cart item with ID " + cartItemId + " has been deleted.");
            } else {
                System.out.println("Cart item with ID " + cartItemId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to delete cart item in the database");
        } finally {
            em.close();
        }
    }

    public void closeDAOResources(){
        em.close();
        emf.close();
    }
}
