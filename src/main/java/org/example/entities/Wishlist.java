package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlist", schema = "vegesfood", indexes = {
        @Index(name = "fk_user_has_product_product2_idx", columnList = "idproduct"),
        @Index(name = "fk_wishlist_user1_idx", columnList = "iduser")
})
public class Wishlist {
    @EmbeddedId
    private WishlistId id;

    @MapsId("idproduct")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idproduct", nullable = false)
    private Product idproduct;

    @MapsId("iduser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "iduser", nullable = false)
    private User iduser;

    public WishlistId getId() {
        return id;
    }

    public void setId(WishlistId id) {
        this.id = id;
    }

    public Product getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Product idproduct) {
        this.idproduct = idproduct;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

}