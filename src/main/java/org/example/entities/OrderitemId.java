package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class OrderitemId implements java.io.Serializable {
    private static final long serialVersionUID = -8530361505811513396L;
    @Column(name = "idorder", nullable = false)
    private Integer idorder;

    @Column(name = "idproduct", nullable = false)
    private Integer idproduct;

    public Integer getIdorder() {
        return idorder;
    }

    public void setIdorder(Integer idorder) {
        this.idorder = idorder;
    }

    public Integer getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Integer idproduct) {
        this.idproduct = idproduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderitemId entity = (OrderitemId) o;
        return Objects.equals(this.idproduct, entity.idproduct) &&
                Objects.equals(this.idorder, entity.idorder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproduct, idorder);
    }

}