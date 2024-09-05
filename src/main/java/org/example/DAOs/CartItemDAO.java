package org.example.DAOs;

import org.example.entities.Cartitem;

public class CartItemDAO extends GenericDAOImpl<Cartitem, Integer> {
    public CartItemDAO(){
        super(Cartitem.class);
    }

}
