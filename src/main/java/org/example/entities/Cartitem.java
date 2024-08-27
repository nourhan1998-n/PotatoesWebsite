package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cartitems")
public class Cartitem {
    @EmbeddedId
    private CartitemId id;

    @MapsId("iduser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "iduser", nullable = false)
    private User iduser;

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

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
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