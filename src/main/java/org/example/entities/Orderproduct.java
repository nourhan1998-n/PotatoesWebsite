package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orderproduct")
public class Orderproduct {
    @EmbeddedId
    private OrderproductId id;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Order order;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    public OrderproductId getId() {
        return id;
    }

    public void setId(OrderproductId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}