package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class CartitemId implements java.io.Serializable {
    private static final long serialVersionUID = -6161938554029636950L;
    @Column(name = "idproduct", nullable = false)
    private Integer idproduct;

    @Column(name = "iduser", nullable = false)
    private Integer iduser;

    public Integer getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Integer idproduct) {
        this.idproduct = idproduct;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CartitemId entity = (CartitemId) o;
        return Objects.equals(this.idproduct, entity.idproduct) &&
                Objects.equals(this.iduser, entity.iduser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproduct, iduser);
    }

}