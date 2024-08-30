package org.example.DAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    private Class<T> entityClass;
    private EntityManagerFactory emf;

    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
    }

    @Override
    public void save(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        entity = em.merge(entity);
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public T findById(ID id) {
        EntityManager em = emf.createEntityManager();
        T entity = em.find(entityClass, id);
        em.close();
        return entity;
    }

    @Override
    public List<T> findAll() {
        EntityManager em = emf.createEntityManager();
        List<T> entities = em.createQuery("from " + entityClass.getName(), entityClass).getResultList();
        em.close();
        return entities;
    }
}

