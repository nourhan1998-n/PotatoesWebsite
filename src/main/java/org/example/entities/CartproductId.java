package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class CartproductId implements java.io.Serializable {
    private static final long serialVersionUID = -7622269814843775509L;
    @Column(name = "cartId", nullable = false)
    private Integer cartId;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "productId", nullable = false)
    private Integer productId;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CartproductId entity = (CartproductId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.cartId, entity.cartId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, cartId, userId);
    }

}