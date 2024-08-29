package org.example.DAOs;

import org.example.entities.Wishlist;  // Make sure the Wishlist entity is properly imported
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.WishlistId;

public class WishlistDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public WishlistDAO(){
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();
        em = emf.createEntityManager();
    }

    public void createWishlist(Wishlist wishlist){
        try {
            em.getTransaction().begin();
            em.persist(wishlist);
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to add wishlist to the database");
        }
    }

    public Wishlist getWishlist(WishlistId wishlistId){
        Wishlist wishlist = null;
        try {
            em.getTransaction().begin();

            // Find the wishlist by ID
            wishlist = em.find(Wishlist.class, wishlistId);

            if (wishlist != null) {
                // Remove the wishlist from the database
                em.remove(wishlist);
                System.out.println("Wishlist with ID " + wishlistId + " was removed.");
            } else {
                System.out.println("Wishlist with ID " + wishlistId + " not found.");
            }

            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to remove wishlist from the database");
        }
        return wishlist;
    }

    public void updateWishlist(Wishlist updatedWishlist) {
        try {
            em.getTransaction().begin();

            // Find the wishlist by ID
            Wishlist oldWishlist = em.find(Wishlist.class, updatedWishlist.getId());

            if (oldWishlist != null) {
                // Update the wishlist's details
                oldWishlist.setIdproduct(updatedWishlist.getIdproduct());
                oldWishlist.setIduser(updatedWishlist.getIduser());
                // Update other fields as needed

                // The changes are automatically tracked and updated in the database upon commit
            } else {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                System.out.println("Wishlist with ID " + updatedWishlist.getId() + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to update wishlist in the database");
        }
    }

    public void deleteWishlist(WishlistId wishlistId) {
        try {
            em.getTransaction().begin();

            // Find the wishlist by ID
            Wishlist wishlist = em.find(Wishlist.class, wishlistId);

            if (wishlist != null) {
                // Remove the wishlist from the database
                em.remove(wishlist);
                System.out.println("Wishlist with ID " + wishlistId + " has been deleted.");
            } else {
                System.out.println("Wishlist with ID " + wishlistId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Failed to delete wishlist in the database");
        } finally {
            em.close();
        }
    }

    public void closeDAOResources(){
        em.close();
        emf.close();
    }
}
