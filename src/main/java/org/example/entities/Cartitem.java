package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cartitems", schema = "vegesfood", indexes = {
        @Index(name = "fk_user_has_product_product1_idx", columnList = "idproduct"),
        @Index(name = "fk_cartItems_user1_idx", columnList = "iduser")
})
public class Cartitem {
    @EmbeddedId
    private CartitemId id;

    @MapsId("idproduct")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idproduct", nullable = false)
    private Product idproduct;

    @MapsId("iduser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "iduser", nullable = false)
    private User iduser;

    @Column(name = "quantity")
    private Integer quantity;

    public CartitemId getId() {
        return id;
    }

    public void setId(CartitemId id) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}