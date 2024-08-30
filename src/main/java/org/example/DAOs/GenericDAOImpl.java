package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    private static final Logger logger = Logger.getLogger(GenericDAOImpl.class.getName());

    private Class<T> entityClass;
    private EntityManagerFactory emf;

    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();    }

    @Override
    public void save(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            logger.info("Entity saved successfully");
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error saving entity", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            logger.info("Entity updated successfully");
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error updating entity", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            entity = em.merge(entity); // Attach the entity to the persistence context if detached
            em.remove(entity);
            em.getTransaction().commit();
            logger.info("Entity deleted successfully");
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error deleting entity", e);
        } finally {
            em.close();
        }
    }

    @Override
    public T findById(ID id) {
        EntityManager em = emf.createEntityManager();
        T entity = null;
        try {
            entity = em.find(entityClass, id);
            if (entity != null) {
                logger.info("Entity found with ID: " + id);
            } else {
                logger.warning("Entity not found with ID: " + id);
            }
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "Error finding entity with ID: " + id, e);
        } finally {
            em.close();
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
        EntityManager em = emf.createEntityManager();
        List<T> entities = null;
        try {
            entities = em.createQuery("from " + entityClass.getName(), entityClass).getResultList();
            logger.info("Entities retrieved successfully");
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "Error retrieving entities", e);
        } finally {
            em.close();
        }
        return entities;
    }
}

