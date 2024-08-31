package org.example.DAOs;

import java.util.List;

public interface GenericDAO<T, ID> {
    void create(T entity);
    void update(T entity);
    void delete(T entity);
    T read(ID id);
    List<T> readAll();
}

