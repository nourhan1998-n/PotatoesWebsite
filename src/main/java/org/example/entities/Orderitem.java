package org.example.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orderitems", schema = "vegesfood", indexes = {
        @Index(name = "fk_order_has_product_order1_idx", columnList = "idorder"),
        @Index(name = "fk_order_has_product_product1_idx", columnList = "idproduct")
})
public class Orderitem {
    @EmbeddedId
    private OrderitemId id;

    @MapsId("idorder")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idorder", nullable = false)
    private Order idorder;

    @MapsId("idproduct")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idproduct", nullable = false)
    private Product idproduct;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price", precision = 10)
    private BigDecimal price;

    public OrderitemId getId() {
        return id;
    }

    public void setId(OrderitemId id) {
        this.id = id;
    }

    public Order getIdorder() {
        return idorder;
    }

    public void setIdorder(Order idorder) {
        this.idorder = idorder;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}