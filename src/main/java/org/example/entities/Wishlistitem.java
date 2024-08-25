package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlistitems")
public class Wishlistitem {
    @EmbeddedId
    private WishlistitemId id;

    @MapsId("idwishlist")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idwishlist", nullable = false)
    private Wishlist idwishlist;

    @MapsId("idproduct")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idproduct", nullable = false)
    private Product idproduct;

    public WishlistitemId getId() {
        return id;
    }

    public void setId(WishlistitemId id) {
        this.id = id;
    }

    public Wishlist getIdwishlist() {
        return idwishlist;
    }

    public void setIdwishlist(Wishlist idwishlist) {
        this.idwishlist = idwishlist;
    }

    public Product getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Product idproduct) {
        this.idproduct = idproduct;
    }

}