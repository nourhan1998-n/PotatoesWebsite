package org.example.DAOs;

import java.util.List;

public interface GenericDAO<T, ID> {
    void save(T entity);
    T update(T entity);
    void delete(T entity);
    T findById(ID id);
    List<T> findAll();
}

