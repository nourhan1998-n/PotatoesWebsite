package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class CartitemId implements java.io.Serializable {
    private static final long serialVersionUID = -9155720688523858914L;
    @Column(name = "idcart", nullable = false)
    private Integer idcart;

    @Column(name = "idproduct", nullable = false)
    private Integer idproduct;

    public Integer getIdcart() {
        return idcart;
    }

    public void setIdcart(Integer idcart) {
        this.idcart = idcart;
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
        CartitemId entity = (CartitemId) o;
        return Objects.equals(this.idproduct, entity.idproduct) &&
                Objects.equals(this.idcart, entity.idcart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproduct, idcart);
    }

}