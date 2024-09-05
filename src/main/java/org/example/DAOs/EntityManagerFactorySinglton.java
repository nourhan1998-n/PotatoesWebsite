package org.example.DAOs;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySinglton {
    private static EntityManagerFactory emf;

    public synchronized EntityManagerFactory getEntityManagerFactory(){
        if(emf == null){
            return emf = Persistence.createEntityManagerFactory("noura");
        }
        return emf;
    }
}