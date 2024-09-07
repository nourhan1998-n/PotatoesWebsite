package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Cartitem;
import org.example.entities.User;

import java.util.List;

public class CartItemDAO extends GenericDAOImpl<Cartitem, Integer> {
    private EntityManagerFactory emf;
    public CartItemDAO(){
        super(Cartitem.class);
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
    public List<Cartitem> findByUser(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cartitem c WHERE c.iduser.id = :userId", Cartitem.class)
                    .setParameter("userId", user.getId())
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void clearCartByUserId(Integer userId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Cartitem c WHERE c.iduser.id = :userId")
                    .setParameter("userId", userId)
                    .executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
