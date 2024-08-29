package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlist")
public class Wishlist {
    @EmbeddedId
    private WishlistId id;

    @MapsId("iduser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "iduser", nullable = false)
    private User iduser;

    @MapsId("idproduct")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idproduct", nullable = false)
    private Product idproduct;

    public WishlistId getId() {
        return id;
    }

    public void setId(WishlistId id) {
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

}