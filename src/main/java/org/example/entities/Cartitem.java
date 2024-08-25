package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cartitems")
public class Cartitem {
    @EmbeddedId
    private CartitemId id;

    @MapsId("idcart")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idcart", nullable = false)
    private Cart idcart;

    @MapsId("idproduct")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idproduct", nullable = false)
    private Product idproduct;

    @Column(name = "quantity")
    private Integer quantity;

    public CartitemId getId() {
        return id;
    }

    public void setId(CartitemId id) {
        this.id = id;
    }

    public Cart getIdcart() {
        return idcart;
    }

    public void setIdcart(Cart idcart) {
        this.idcart = idcart;
    }

    public Product getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Product idproduct) {
        this.idproduct = idproduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}