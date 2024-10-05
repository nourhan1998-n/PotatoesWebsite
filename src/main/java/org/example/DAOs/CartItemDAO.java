package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import org.example.entities.Cartitem;
import org.example.entities.CartitemId;
import org.example.entities.Product;
import org.example.entities.User;
import jakarta.persistence.Query;


import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CartItemDAO extends GenericDAOImpl<Cartitem, CartitemId> {

    private EntityManagerFactory emf;
    private static final Logger logger = Logger.getLogger(GenericDAOImpl.class.getName());

    public CartItemDAO(){
        super(Cartitem.class);
        this.emf =  new EntityManagerFactorySinglton().getEntityManagerFactory();

    }

    public Cartitem findByUserIdAndProductId(Integer userId, Integer productId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cartitem c WHERE c.iduser.id = :userId AND c.idproduct.id = :productId", Cartitem.class)
                    .setParameter("userId", userId)
                    .setParameter("productId", productId)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Cartitem> findByUserId(Integer userId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cartitem c WHERE c.iduser.id = :userId", Cartitem.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void updateByUserAndProduct(Integer userId, List<Cartitem> newCart) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Step 1: Delete existing cart items for the user
            Query deleteQuery = em.createQuery("DELETE FROM Cartitem c WHERE c.iduser.id = :userId");
            deleteQuery.setParameter("userId", userId);
            deleteQuery.executeUpdate();

            // Step 2: Insert new cart items
            for (Cartitem cartitem : newCart) {
                cartitem.setIdproduct(em.merge(cartitem.getIdproduct()));
                cartitem.setIduser(em.merge(cartitem.getIduser()));
                em.persist(cartitem);
            }

            em.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
//            System.out.println("Error in updating user cart"+  e.printStackTrace());
            em.getTransaction().rollback();
        }
        finally {

            em.close();
        }
    }


}
