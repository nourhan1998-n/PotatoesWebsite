package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class WishlistitemId implements java.io.Serializable {
    private static final long serialVersionUID = 426550953089855999L;
    @Column(name = "idwishlist", nullable = false)
    private Integer idwishlist;

    @Column(name = "idproduct", nullable = false)
    private Integer idproduct;

    public Integer getIdwishlist() {
        return idwishlist;
    }

    public void setIdwishlist(Integer idwishlist) {
        this.idwishlist = idwishlist;
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
        WishlistitemId entity = (WishlistitemId) o;
        return Objects.equals(this.idproduct, entity.idproduct) &&
                Objects.equals(this.idwishlist, entity.idwishlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproduct, idwishlist);
    }

}