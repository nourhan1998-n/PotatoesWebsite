package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cartproduct")
public class Cartproduct {
    @EmbeddedId
    private CartproductId id;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cart cart;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    public CartproductId getId() {
        return id;
    }

    public void setId(CartproductId id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}