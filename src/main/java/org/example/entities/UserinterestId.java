package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class UserinterestId implements java.io.Serializable {
    private static final long serialVersionUID = 379505028084950657L;
    @Column(name = "iduser", nullable = false)
    private Integer iduser;

    @Column(name = "idcategory", nullable = false)
    private Integer idcategory;

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public Integer getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Integer idcategory) {
        this.idcategory = idcategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserinterestId entity = (UserinterestId) o;
        return Objects.equals(this.iduser, entity.iduser) &&
                Objects.equals(this.idcategory, entity.idcategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, idcategory);
    }

}